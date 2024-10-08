package org.example.bookingplatform.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookingResponseDTO {
    private int totalTickets;
    private double ticketPrice;
    private double totalCost;

    // Getters and Setters
}
