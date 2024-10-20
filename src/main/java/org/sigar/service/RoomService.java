package org.sigar.service;

import org.sigar.dto.DTOConverter;
import org.sigar.dto.RoomResponseDTO;
import org.sigar.model.Guest;
import org.sigar.model.Room;
import org.sigar.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final GuestRoomManager guestRoomManager;

    @Autowired
    public RoomService(RoomRepository roomRepository,GuestRoomManager guestRoomManager){
        this.roomRepository = roomRepository;
        this.guestRoomManager = guestRoomManager;
    }

    public List<RoomResponseDTO> getAllRooms(){
        List<Room> rooms =  roomRepository.findAll();
        return DTOConverter.convertToRoomDTO(rooms);
    }

    public Optional<Room> addGuestToRoom(Long roomId, Guest guest){
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if(roomOptional.isPresent()){
            Room room = roomOptional.get();
            room.addGuest(guest);
            roomRepository.save(room);
        }
        return roomOptional;
    }
    public Room addRoom(Room room){
        return roomRepository.save(room);
    }
    public boolean removeRoom(Long roomId){
        return guestRoomManager.removeRoom(roomId);
    }
}





//    public Optional<Room> removeGuestFromRoom(Long guestId){
//        Guest guest = guestService.g
//        if(roomOptional.isPresent()){
//            Room room = roomOptional.get();
//            Optional<Guest> guestOptional = room.getGuests()
//            room.(guest);
//            roomRepository.save(room);
//        }
//        return roomOptional;
//    }
