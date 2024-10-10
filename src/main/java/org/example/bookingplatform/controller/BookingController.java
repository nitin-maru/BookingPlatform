package org.example.bookingplatform.controller;

import org.example.bookingplatform.Dtos.BookingRequestDTO;
import org.example.bookingplatform.Dtos.BookingResponseDTO;
import org.example.bookingplatform.Dtos.BookingServiceResponse;
import org.example.bookingplatform.Entity.Booking;
import org.example.bookingplatform.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @GetMapping
    public ResponseEntity<List<BookingServiceResponse>> getAllBookings() {
        List<BookingServiceResponse> response =  bookingService.getAllBookings();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public BookingServiceResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);

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
