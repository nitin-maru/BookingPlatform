package org.example.bookingplatform.service;

import org.example.bookingplatform.Dtos.BookingRequestDTO;
import org.example.bookingplatform.Dtos.BookingResponseDTO;
import org.example.bookingplatform.Dtos.BookingServiceResponse;
import org.example.bookingplatform.Entity.Booking;

import java.util.List;

public interface BookingService {
    List<BookingServiceResponse> getAllBookings();

    BookingServiceResponse getBookingById(Long id);

    Booking createBooking(Booking booking);

    Booking updateBooking(Long id, Booking booking);

    void deleteBooking(Long id);

    BookingResponseDTO calculateBooking(BookingRequestDTO bookingRequest);

    BookingResponseDTO calculateAfternoonDiscount(BookingRequestDTO bookingRequest);
}
