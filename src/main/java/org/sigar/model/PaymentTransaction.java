package org.sigar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.sigar.Constants.enums.TransactionType;

import java.time.LocalDate;

@Entity
@Data
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long transactionID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_contract_id",referencedColumnName = "rental_contract_id")
    @JsonBackReference
    private RentalContract rentalContract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",referencedColumnName = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id",referencedColumnName = "guest_id")
    private Guest guest;

    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String notes;
    private Integer amount;

    // Factory method for creating a rent payment transaction
    public static PaymentTransaction createPaymentTransaction(RentalContract rentalContract, int amount, TransactionType transactionType, String notes) {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.rentalContract = rentalContract;
        transaction.transactionDate = LocalDate.now();
        transaction.transactionType = transactionType;  // Enum for different types of payments
        transaction.amount = amount;
        transaction.notes = notes;
        return transaction;
    }
}
