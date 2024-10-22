package org.sigar.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class RentalContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_contract_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @OneToMany(mappedBy = "rentalContract",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PaymentTransaction> transactions;


    private Integer roomNumber;
    private String guestName;
    private Integer advanceAmountPaid;
    private LocalDate rentDueDate;
    private Double monthlyRentAmount;

//     Methods to check rent due date, update payment, etc.
//     public void  updateRentDueDate() {
//        this.rentDueDate = rentDueDate.plusMonths(1);
//    }
//
//    public boolean isRentDue() {
//        return LocalDate.now().isAfter(rentDueDate);
//    }

}

