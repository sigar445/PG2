package org.sigar.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.sigar.model.Guest;
import org.sigar.model.PaymentTransaction;
import org.sigar.model.Room;

import java.time.LocalDate;
import java.util.List;

public record RentalContractResponseDTO (
        Long id,
        Integer roomNumber,
        String guestName,
        Integer advanceAmountPaid,
        LocalDate rentDueDate,
        Double monthlyRentAmount
){
}
