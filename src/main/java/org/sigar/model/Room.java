package org.sigar.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long roomId;

    private int roomNumber;
    private int floor;
    @JsonManagedReference
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Guest> guests;
}
