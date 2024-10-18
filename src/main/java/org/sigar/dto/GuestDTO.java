package org.sigar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GuestDTO {
    private long guestId;
    private String name;
    private Integer age;
    private long roomId;
    private long roomNumber;
    private long floor;

}
