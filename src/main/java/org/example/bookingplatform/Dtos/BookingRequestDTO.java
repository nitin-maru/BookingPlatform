package org.example.bookingplatform.Dtos;

import lombok.Data;
import org.example.bookingplatform.Entity.Booking;

import java.time.LocalDateTime;

@Data
public class BookingRequestDTO {
    private int totalTickets;
    private double ticketPrice;
    private LocalDateTime showTime;  // Add showtime for discount logic

    // Constructors, Getters, and Setters
}

