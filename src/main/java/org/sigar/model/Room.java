package org.sigar.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sigar.Constants.enums.Beds;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long roomId;

    private int roomNumber;
    private int floor;
    private boolean hasKitchen;
    private boolean hasAC;
    private boolean isAvailable;
    private Integer rent;
    @Enumerated(EnumType.STRING)
    private Beds beds;

    @JsonManagedReference
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Guest> guests;

    public Room(long roomId,int roomNumber,int floor){
        this.roomId  = roomId;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public void addGuest(Guest guest) {
        this.guests.add(guest);
        this.setAvailable(false);
        guest.setRoom(this);
    }

    private void removeGuest(Guest guest){
        this.guests.remove(guest);
        this.setAvailable(true);
        guest.setRoom(this);
    }
}
