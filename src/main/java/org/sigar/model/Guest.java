package org.sigar.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
    @Id
    @Column(name = "guest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guestId;

    private String name;
    private Integer age;

    @Column( length = 20)
    private String phoneNumber;
    private LocalDate dateOfOccupancy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id",referencedColumnName = "room_id")
    @JsonBackReference
    private Room room;
}
