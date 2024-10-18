package org.sigar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class RoomDTO {
    private long roomId;
    private int roomNumber;
    private int floor;
    private List<String> guestNames;
}
