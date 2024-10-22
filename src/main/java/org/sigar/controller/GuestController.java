package org.sigar.controller;

import org.sigar.dto.GuestResponseDTO;
import org.sigar.model.Guest;
import org.sigar.service.GuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/PG2/guests")
public class GuestController {

    private final GuestService guestService;
    private static final Logger logger = LoggerFactory.getLogger(GuestController.class);
    @Autowired
    public GuestController(GuestService guestService){
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> findAllGuests(){
        logger.info("Fetching all guests");
        List<GuestResponseDTO> guests = guestService.getAllGuests();
        if (guests.isEmpty()) {
            logger.info("No guests found");
            return ResponseEntity.noContent().build();  // 204 NO CONTENT if no guests found
        }
        logger.info("Returning all guests");
        return ResponseEntity.ok(guests);
    }

    @GetMapping("/age")
    public ResponseEntity<List<GuestResponseDTO>> getGuestsByAgeRange(
            @RequestParam("startAge") Optional<Integer>  startAge,
            @RequestParam("endAge") Optional<Integer>  endAge){
        if (startAge.isEmpty() || endAge.isEmpty() || startAge.get() > endAge.get()) {
            logger.warn("Invalid age range parameters: startAge={}, endAge={}", startAge, endAge);
            return ResponseEntity.badRequest().body(Collections.emptyList());  // 400 BAD REQUEST for invalid input
        }
        logger.info("Fetching guests between ages {} and {}", startAge.get(), endAge.get());
        List<GuestResponseDTO> guests = guestService.getGuestsByAgeRange(startAge.get(), endAge.get());
        if (guests.isEmpty()) {

            logger.info("No guests found for age range {} to {}", startAge.get(), endAge.get());
            return ResponseEntity.noContent().build();  // 204 NO CONTENT if no guests found
        }
        return ResponseEntity.ok(guests);
    }


    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest){
        Guest savedGuest = guestService.addGuest(guest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuest);
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<String> removeGuest(@PathVariable Long guestId){
        boolean isRemoved = guestService.removeGuest(guestId);
        if (isRemoved) {
            return ResponseEntity.ok("Guest with ID " + guestId + " removed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Guest with ID " + guestId + " not found.");
        }
    }


    @GetMapping("/occupancy")
    public ResponseEntity<List<GuestResponseDTO>> getGuestsBetweenDateOfOccupancy(
            @RequestParam("startDate") Optional<LocalDate> startDate,
            @RequestParam("endDate") Optional<LocalDate> endDate){

        if(startDate.isEmpty() || endDate.isEmpty() || startDate.get().isAfter(endDate.get())){
            logger.info("Invalid age range parameters: startAge={}, endAge={}", startDate, endDate);
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        logger.info("Finding Guests with dates of occupancy between {} and {}",startDate.get(),endDate.get());
        List<GuestResponseDTO> guests = guestService.getGuestsBetweenDateOfOccupancy(
                startDate.get(),
                endDate.get());

        if(guests.isEmpty()){
            logger.info("No guests found for date range {} to {}", startDate.get(), endDate.get());
            ResponseEntity.badRequest().body(Collections.emptyList());
        }
        return ResponseEntity.ok(guests);
    }
    // Optional: Add error handling methods
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the error (you can use a logger here)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    @GetMapping("/guests-page")
    public String guestPage() {
        return "guests";  // This looks for the guests.html in the templates folder
    }

}
