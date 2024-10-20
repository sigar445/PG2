package org.sigar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class RoomResponseDTO {
    private long roomId;
    private int roomNumber;
    private int floor;
    boolean isAvailable;
    private List<String> guestNames;
}
