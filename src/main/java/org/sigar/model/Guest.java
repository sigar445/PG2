package org.sigar.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Guest {
    @Id
    @Column(name = "guest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guestId;

    private String name;
    private Integer age;
}
