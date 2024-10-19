package org.sigar.dto;

import org.sigar.model.Guest;
import org.sigar.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DTOConverter {
    public static RoomResponseDTO convertToRoomDTO(Room room){
        List<String> guests = room.getGuests()
                .stream()
                .map(Guest::getName)
                .toList();
        return new RoomResponseDTO(room.getRoomId(), room.getRoomNumber(), room.getFloor(),guests);
    }
    public static List<RoomResponseDTO> convertToRoomDTO(List<Room> rooms){
        if(rooms == null) return Collections.emptyList();
        List<RoomResponseDTO> responseDTOList = new ArrayList<>();
        for(Room room : rooms){
        List<String> guests = room.getGuests()
                .stream()
                .map(Guest::getName)
                .toList();

            responseDTOList.add(new RoomResponseDTO(
                    room.getRoomId(),
                    room.getRoomNumber(),
                    room.getFloor(),
                    guests
            ));
        }
        return responseDTOList;
    }

    public static GuestResponseDTO covertToGuestDTO(Guest guest){
        Room room = guest.getRoom();
        return new GuestResponseDTO(guest.getGuestId(),guest.getName(), guest.getAge(), room.getRoomId(), room.getRoomNumber(),
                room.getFloor(), guest.getPhoneNumber(),guest.getDateOfOccupancy());
    }
    public static List<GuestResponseDTO> covertToGuestDTO(List<Guest> guests){
        if (guests == null) return Collections.emptyList();
        return guests.stream()
                .map(guest -> {
                    Room room = guest.getRoom();
                    return new GuestResponseDTO(
                            guest.getGuestId(),
                            guest.getName(),
                            guest.getAge(),
                            room.getRoomId(),
                            room.getRoomNumber(),
                            room.getFloor(),
                            guest.getPhoneNumber(),
                            guest.getDateOfOccupancy()
                    );
                })
                .toList();
    }

}
