package org.example.bookingplatform.service;

import org.example.bookingplatform.Dtos.BookingRequestDTO;
import org.example.bookingplatform.Dtos.BookingResponseDTO;
import org.example.bookingplatform.Entity.Booking;
import org.example.bookingplatform.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        return bookingRepository.findById(id).map(existingBooking -> {
            existingBooking.setCustomer(booking.getCustomer());
            existingBooking.setMovie(booking.getMovie());
            existingBooking.setTheater(booking.getTheater());
            existingBooking.setScreen(booking.getScreen());
            existingBooking.setShowTime(booking.getShowTime());
            existingBooking.setTotalTickets(booking.getTotalTickets());
            return bookingRepository.save(existingBooking);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public BookingResponseDTO calculateBooking(BookingRequestDTO bookingRequest) {
        int totalTickets = bookingRequest.getTotalTickets();
        double ticketPrice = bookingRequest.getTicketPrice();
        double totalCost = 0;

        if (totalTickets >= 3) {
            // Apply 50% discount to the third ticket
            totalCost = (2 * ticketPrice) + (0.5 * ticketPrice) + ((totalTickets - 3) * ticketPrice);
        } else {
            // No discount if less than 3 tickets
            totalCost = totalTickets * ticketPrice;
        }

        // Return the calculated total
        return new BookingResponseDTO(totalTickets, ticketPrice, totalCost);
    }

    public BookingResponseDTO calculateAfternoonDiscount(BookingRequestDTO bookingRequest) {
        int totalTickets = bookingRequest.getTotalTickets();
        double ticketPrice = bookingRequest.getTicketPrice();
        LocalDateTime showTime = bookingRequest.getShowTime();
        double totalCost;

        // Check if the show is in the afternoon (12 PM to 5 PM)
        if (showTime.getHour() >= 12 && showTime.getHour() < 17) {
            // Apply a 20% discount for the afternoon show
            totalCost = totalTickets * ticketPrice * 0.8;
        } else {
            // No discount for non-afternoon shows
            totalCost = totalTickets * ticketPrice;
        }

        // Return the calculated total
        return new BookingResponseDTO(totalTickets, ticketPrice, totalCost);
    }
}
