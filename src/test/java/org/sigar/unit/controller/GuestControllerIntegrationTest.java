package org.sigar.unit.controller;

import org.junit.jupiter.api.Test;
import org.sigar.dto.GuestResponseDTO;
import org.sigar.model.Guest;
import org.sigar.model.Room;
import org.sigar.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
@SpringBootTest
@AutoConfigureMockMvc
public class GuestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Test
    public void testGetAllGuests() throws Exception {
        List<GuestResponseDTO> guestDTOs = List.of(new GuestResponseDTO(1L, "Alice", 25, 1L, 101, 1));

        when(guestService.getAllGuests()).thenReturn(guestDTOs);

        mockMvc.perform(get("/api/PG2/guests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].roomNumber").value(101));
    }

    @Test
    public void testAddGuest() throws Exception {
        Guest guest = new Guest(0, "John", 30, new Room(1L, 101, 1));

        when(guestService.addGuest(any(Guest.class))).thenReturn(guest);

        mockMvc.perform(post("/api/PG2/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"age\":30,\"room\":{\"roomId\":1}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.age").value(30));
    }
}
