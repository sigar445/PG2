package org.sigar.unit.service;

import org.junit.jupiter.api.Test;
import org.sigar.dto.GuestResponseDTO;
import org.sigar.model.Guest;
import org.sigar.model.Room;
import org.sigar.repo.GuestRepository;
import org.sigar.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GuestServiceTest {

    @MockBean
    private GuestRepository guestRepository;

    @Autowired
    private GuestService guestService;

    @Test
    public void testAddGuest(){

        Guest guest = new Guest();
        guest.setName("John");
        guest.setAge(30);
        Room room = new Room();
        guest.setRoom(room);

        when(guestRepository.saveAndFlush(guest)).thenReturn(guest);
    }
    @Test
    public void testGetAllGuests() {
        List<Guest> guests = List.of(new Guest(1L, "Alice",
                25, new Room()));
        when(guestRepository.findAll()).thenReturn(guests);

        List<GuestResponseDTO> guestDTOs = guestService.getAllGuests();
        assertEquals(1, guestDTOs.size());
        assertEquals("Alice", guestDTOs.get(0).getName());
    }
}
