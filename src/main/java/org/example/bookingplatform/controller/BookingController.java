package org.example.bookingplatform.controller;

import org.example.bookingplatform.Dtos.BookingRequestDTO;
import org.example.bookingplatform.Dtos.BookingResponseDTO;
import org.example.bookingplatform.Entity.Booking;
import org.example.bookingplatform.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;


    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking) != null ?
                ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/calculate")
    public ResponseEntity<BookingResponseDTO> calculateBooking(@RequestBody BookingRequestDTO bookingRequest) {
        BookingResponseDTO response = bookingService.calculateBooking(bookingRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/calculate-with-afternoon-discount")
    public ResponseEntity<BookingResponseDTO> calculateAfternoonDiscount(@RequestBody BookingRequestDTO bookingRequest) {
        BookingResponseDTO response = bookingService.calculateAfternoonDiscount(bookingRequest);
        return ResponseEntity.ok(response);
    }
}
