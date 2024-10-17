package org.sigar.service;

import org.sigar.model.Guest;
import org.sigar.repo.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests(){
        return guestRepository.findAll();
    }
    public Guest addGuest(Guest guest){
        return guestRepository.saveAndFlush(guest);
    }

}
