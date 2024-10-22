package org.sigar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

public record GuestResponseDTO(
        long guestId,
        String name,
        Integer age,
        Long roomId,
        Integer roomNumber,
        Integer floor,
        String phoneNumber,
        LocalDate dateOfOccupancy
) {
    // The record is immutable and automatically creates constructor, getters, toString, equals, and hashCode.
}

