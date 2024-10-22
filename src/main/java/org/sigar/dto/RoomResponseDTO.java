package org.sigar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.sigar.Constants.enums.Beds;

import java.util.List;

public record RoomResponseDTO (
    long roomId,
    Integer roomNumber,
    Integer floor,
    Boolean hasKitchen,
    Boolean hasAc,
    Boolean isAvailable,
    Integer rent,
    Beds beds,
    List<String> guestNames){
}