package org.sigar.controller;

import org.sigar.dto.GuestResponseDTO;
import org.sigar.model.Guest;
import org.sigar.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/PG2/guests")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService){
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> getAllGuests(){
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest){
        Guest savedGuest = guestService.addGuest(guest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuest);
    }

    // Optional: Add error handling methods
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the error (you can use a logger here)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
