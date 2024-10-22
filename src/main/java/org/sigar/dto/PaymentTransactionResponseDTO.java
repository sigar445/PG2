package org.sigar.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.sigar.Constants.enums.TransactionType;
import org.sigar.model.Guest;
import org.sigar.model.RentalContract;
import org.sigar.model.Room;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record PaymentTransactionResponseDTO(
        Long transactionId,
        Integer roomNumber,
        String guestName,
        LocalDate transactionDate,
        TransactionType transactionType,
        Integer amount
) {
}
