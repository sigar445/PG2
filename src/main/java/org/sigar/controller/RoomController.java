package org.sigar.controller;

import org.sigar.dto.RoomResponseDTO;
import org.sigar.model.Guest;
import org.sigar.model.Room;
import org.sigar.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/PG2/rooms")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.addRoom(room));
    }

    @PostMapping("/{roomId}/guests")
    public ResponseEntity<String> addGuestToRoom(@PathVariable Long roomId,
                                                 @RequestBody Guest guest) {

        Optional<Room> roomOptional = roomService.addGuestToRoom(roomId, guest);
        if (roomOptional.isEmpty()) {
            logger.info("Room not found with ID " + roomId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room with ID " + roomId + " not found ");
        } else {
            logger.info("Guest successfully added to room  ID " + roomId);

            return ResponseEntity.ok("Guest successfully added to room with ID " + roomId);
        }
    }

}










//    @PostMapping("/{roomId}/{guestId}")
//    public ResponseEntity<String> removeGuestFromRoom(@PathVariable Long roomId,
//                                                 @PathVariable Long guestId){
//
//        Optional<Room> roomOptional = roomService.removeGuestFromRoom(guestId);
//        if(roomOptional.isEmpty()){
//            logger.info("Room not found with ID "+ roomId);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room with ID " + roomId + " not found ");
//        }else{
//            logger.info("Guest successfully added to room  ID "+ roomId);
//
//            return ResponseEntity.ok("Guest successfully added to room with ID "+ roomId);
//        }
//    }

