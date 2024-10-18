package org.sigar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GuestResponseDTO {
    private long guestId;
    private String name;
    private Integer age;
    private long roomId;
    private long roomNumber;
    private long floor;

}
