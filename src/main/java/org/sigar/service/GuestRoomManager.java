package org.sigar.service;

import org.sigar.repo.GuestRepository;
import org.sigar.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestRoomManager {
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    @Autowired
    public GuestRoomManager(GuestRepository guestRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }
}
