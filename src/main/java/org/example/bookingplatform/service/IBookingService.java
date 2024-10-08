package org.example.bookingplatform.service;

import org.example.bookingplatform.Entity.Booking;

import java.util.List;
import java.util.Optional;

public interface IBookingService {
    List<Booking> getAllBookings();

    Optional<Booking> getBookingById(Long id);

    Booking createBooking(Booking booking);

    Booking updateBooking(Long id, Booking booking);

    void deleteBooking(Long id);
}
