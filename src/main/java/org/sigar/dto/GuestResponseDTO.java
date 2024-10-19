package org.sigar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

public record GuestResponseDTO(
        long guestId,
        String name,
        Integer age,
        long roomId,
        long roomNumber,
        long floor,
        String phoneNumber,
        LocalDate dateOfOccupancy
) {
    // The record is immutable and automatically creates constructor, getters, toString, equals, and hashCode.
}

