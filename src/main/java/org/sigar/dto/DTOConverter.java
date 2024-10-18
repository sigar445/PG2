package org.sigar.dto;

import org.sigar.model.Guest;
import org.sigar.model.Room;

import java.util.List;

public class DTOConverter {
    public static RoomResponseDTO convertToRoomDTO(Room room){
        List<String> guests = room.getGuests()
                .stream()
                .map(Guest::getName)
                .toList();
        return new RoomResponseDTO(room.getRoomId(), room.getRoomNumber(), room.getFloor(),guests);
    }

    public static GuestResponseDTO covertToGuestDTO(Guest guest){
        Room room = guest.getRoom();
        return new GuestResponseDTO(guest.getGuestId(),guest.getName(), guest.getAge(), room.getRoomId(), room.getRoomNumber(),
                room.getFloor());
    }

}
