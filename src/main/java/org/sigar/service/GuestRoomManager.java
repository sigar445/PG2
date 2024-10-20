package org.sigar.service;

import org.sigar.model.Guest;
import org.sigar.model.Room;
import org.sigar.repo.GuestRepository;
import org.sigar.repo.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestRoomManager {
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(GuestRoomManager.class);

    @Autowired
    public GuestRoomManager(GuestRepository guestRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }
    public boolean removeGuest(Long guestId){
        Optional<Guest> guestOptional = guestRepository.findById(guestId);
        if(guestOptional.isEmpty()){
            logger.info("No guest found with ID " + guestId);
            return false;
        }
        Guest guest = guestOptional.get();
        Room room = guest.getRoom();
        if(room != null){
            room.removeGuest(guest);
            roomRepository.saveAndFlush(room);
        }
        guestRepository.deleteById(guestId);
        return true;
    }
    public boolean removeRoom(Long roomId){
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if(roomOptional.isEmpty()){
            logger.info("No room found with ID " + roomId);
            return false;
        }
        Room room = roomOptional.get();
        room.getGuests().forEach(guest -> guest.setRoom(null));
        roomRepository.deleteById(roomId);;
        return true;
    }
}
