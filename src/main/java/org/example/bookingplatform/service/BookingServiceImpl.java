package org.example.bookingplatform.service;

import org.example.bookingplatform.Dtos.BookingRequestDTO;
import org.example.bookingplatform.Dtos.BookingResponseDTO;
import org.example.bookingplatform.Dtos.BookingServiceResponse;
import org.example.bookingplatform.Entity.Booking;
import org.example.bookingplatform.repository.BookingRepository;
import org.example.bookingplatform.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<BookingServiceResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    // Helper method to convert Booking entity to BookingResponse DTO
    private BookingServiceResponse convertToResponse(Booking booking) {
        return new BookingServiceResponse(
                booking.getId(),
                booking.getShowTime().format(DateTimeFormatter.ofPattern("HH-MM-YYYY HH:MM:SS")),
                booking.getCustomer().getId(),
                booking.getCustomer().getName(),
                booking.getCustomer().getEmail()
        );
    }

    @Override
    public BookingServiceResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if(booking != null) {
            return this.convertToResponse(booking);
        }
        return null;
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
