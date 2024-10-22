package org.sigar.dto;

import org.sigar.model.Guest;
import org.sigar.model.PaymentTransaction;
import org.sigar.model.RentalContract;
import org.sigar.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DTOConverter {
    public static RoomResponseDTO convertToRoomDTO(Room room){
        List<String> guests = room.getGuests()
                .stream()
                .map(Guest::getName)
                .toList();

        return new RoomResponseDTO(
                room.getRoomId(),
                room.getRoomNumber(),
                room.getFloor(),
                room.isAvailable(),
                guests
        );
    }
    public static List<RoomResponseDTO> convertToRoomDTO(List<Room> rooms){
        if(rooms == null) return Collections.emptyList();
        List<RoomResponseDTO> responseDTOList = new ArrayList<>();
        return rooms.stream()
                .map(DTOConverter::convertToRoomDTO)
                .toList();
    }

    public static GuestResponseDTO covertToGuestDTO(Guest guest){
        Room room = guest.getRoom();
        Long roomId = room != null ? room.getRoomId():null;
        Integer roomNumber = room != null ? room.getRoomNumber():null;
        Integer floor = room != null ? room.getFloor():null;
        return new GuestResponseDTO(guest.getGuestId(),
                guest.getName(),
                guest.getAge(),
                roomId,
                roomNumber,
                floor,
                guest.getPhoneNumber(),
                guest.getDateOfOccupancy());
    }
    public static List<GuestResponseDTO> covertToGuestDTO(List<Guest> guests){
        if (guests == null) return Collections.emptyList();
        return guests.stream()
                .map(DTOConverter::covertToGuestDTO)
                .toList();
    }

    public static PaymentTransactionResponseDTO convertToPaymentTransactionResponseDTO(PaymentTransaction paymentTransaction){
        Room room = paymentTransaction.getRoom();
        Integer roomNumber = room !=null ? room.getRoomNumber():-1;
        Guest guest = paymentTransaction.getGuest();
        String guestName = guest != null ? guest.getName() : "N/A";
        return new PaymentTransactionResponseDTO(
                paymentTransaction.getTransactionID(),
                roomNumber,
                guestName,
                paymentTransaction.getTransactionDate(),
                paymentTransaction.getTransactionType(),
                paymentTransaction.getAmount());
    }

    public static RentalContractResponseDTO convertToRentalContractResponseDTO(RentalContract rentalContract) {
        return new RentalContractResponseDTO(
                rentalContract.getId(),
                rentalContract.getRoomNumber(),
                rentalContract.getGuestName(),
                rentalContract.getAdvanceAmountPaid(),
                rentalContract.getRentDueDate(),
                rentalContract.getMonthlyRentAmount());
    }
}
