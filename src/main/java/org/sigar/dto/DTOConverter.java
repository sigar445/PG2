package org.sigar.dto;

import org.sigar.model.Guest;
import org.sigar.model.Room;

import java.util.List;

public class DTOConverter {
    public static RoomDTO convertToRoomDTO(Room room){
        List<String> guests = room.getGuests()
                .stream()
                .map(Guest::getName)
                .toList();
        return new RoomDTO(room.getRoomId(), room.getRoomNumber(), room.getFloor(),guests);
    }

    public static GuestDTO covertToGuestDTO(Guest guest){
        Room room = guest.getRoom();


        return new GuestDTO(guest.getGuestId(),guest.getName(), guest.getAge(), room.getRoomId(), room.getRoomNumber(),
                room.getFloor());
    }

}
