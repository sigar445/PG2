package org.sigar.service;

import org.sigar.dto.DTOConverter;
import org.sigar.dto.GuestResponseDTO;
import org.sigar.model.Guest;
import org.sigar.repo.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }

    public List<GuestResponseDTO> getAllGuests(){
        //List<Guest> guests =  guestRepository.findAll();

        return DTOConverter.covertToGuestDTO(guestRepository.findAll());
    }
    public Guest addGuest(Guest guest){
        return guestRepository.saveAndFlush(guest);
    }
    public List<GuestResponseDTO> getGuestsByAgeRange(Integer startAge, Integer endAge){
        List<Guest> guests = guestRepository.findByAgeBetween(startAge,endAge);
        return DTOConverter.covertToGuestDTO(guests);
    }

    public List<GuestResponseDTO> getGuestsBetweenDateOfOccupancy(LocalDate startDate,LocalDate endDate){
        List<Guest> guests = guestRepository.findByDateOfOccupancyBetween(startDate,endDate);
        return DTOConverter.covertToGuestDTO(guests);
    }


}
