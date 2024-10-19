package org.sigar.unit.service;

import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GuestServiceTest {

    @MockBean
    private GuestRepository guestRepository;

    @Autowired
    private GuestService guestService;
    private Guest guest;
    private Room room;
    @BeforeEach
    public void setUp() {
        // Initialize guest and room objects for reuse
        guest = new Guest();
        guest.setName("John");
        guest.setAge(30);
        room = new Room();
        guest.setRoom(room);
    }
    @Test
    public void testAddGuest() {
        // Mock the behavior of saveAndFlush method
        when(guestRepository.saveAndFlush(guest)).thenReturn(guest);

        // Call the service method to add a guest
        Guest savedGuest = guestService.addGuest(guest);

        // Assertions to verify correct guest details
        assertNotNull(savedGuest);
        assertEquals("John", savedGuest.getName());
        assertEquals(30, savedGuest.getAge());
        assertEquals(room, savedGuest.getRoom());

        // Verify that the repository's saveAndFlush method was called
        verify(guestRepository, times(1)).saveAndFlush(guest);
    }

//    @Test
//    public void testGetAllGuests() {
//        // Mock repository response
//        List<Guest> guests = List.of(new Guest(1L, "Alice", 25, new Room()));
//        when(guestRepository.findAll()).thenReturn(guests);
//
//        // Call the service method to get all guests
//        List<GuestResponseDTO> guestDTOs = guestService.getAllGuests();
//
//        // Assertions to check correct behavior
//        assertEquals(1, guestDTOs.size());
//        assertEquals("Alice", guestDTOs.get(0).getName());
//        assertEquals(25, guestDTOs.get(0).getAge());
//
//        // Verify that the repository's findAll method was called
//        verify(guestRepository, times(1)).findAll();
//    }
}
