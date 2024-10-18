package org.sigar.service;

import org.sigar.dto.DTOConverter;
import org.sigar.dto.RoomResponseDTO;
import org.sigar.model.Room;
import org.sigar.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public List<RoomResponseDTO> getAllRooms(){
        List<Room> rooms =  roomRepository.findAll();
        return rooms.stream()
                .map(DTOConverter::convertToRoomDTO)
                .toList();

    }

    public Room addRoom(Room room){
        return roomRepository.save(room);
    }


}
