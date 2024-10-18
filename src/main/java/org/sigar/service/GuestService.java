package org.sigar.service;

import org.sigar.dto.DTOConverter;
import org.sigar.dto.GuestResponseDTO;
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

    public List<GuestResponseDTO> getAllGuests(){
        List<Guest> guests =  guestRepository.findAll();
        return guests.stream()
                .map(DTOConverter::covertToGuestDTO)
                .toList();
    }
    public Guest addGuest(Guest guest){
        return guestRepository.saveAndFlush(guest);
    }

}
