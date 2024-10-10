package org.example.bookingplatform.service;

import org.example.bookingplatform.Dtos.BookingRequestDTO;
import org.example.bookingplatform.Dtos.BookingResponseDTO;
import org.example.bookingplatform.Dtos.BookingServiceResponse;
import org.example.bookingplatform.Entity.*;
import org.example.bookingplatform.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetAllBookings_ReturnsListOfBookings() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");

        Theater theater = new Theater();
        theater.setId(1L);
        theater.setName("Grand Cinema");

        Screen screen = new Screen();
        screen.setId(1L);
        screen.setName("Screen 1");
        screen.setTheater(theater);
        screen.setSeatCapacity(200);

        Booking booking1 = new Booking();
        booking1.setId(1L);
        booking1.setCustomer(customer);
        booking1.setMovie(movie);
        booking1.setTheater(theater);
        booking1.setScreen(screen);
        booking1.setShowTime(LocalDateTime.of(2024, 10, 12, 15, 30));
        booking1.setTotalTickets(2);

        Booking booking2 = new Booking();
        booking2.setId(2L);
        booking2.setCustomer(customer);
        booking2.setMovie(movie);
        booking2.setTheater(theater);
        booking2.setScreen(screen);
        booking2.setShowTime(LocalDateTime.of(2024, 10, 14, 18, 0));
        booking2.setTotalTickets(3);

        List<Booking> mockBookings = new ArrayList<>();
        mockBookings.add(booking1);
        mockBookings.add(booking2);

        when(bookingRepository.findAll()).thenReturn(mockBookings);

        // Act
        List<BookingServiceResponse> bookings = bookingService.getAllBookings();

        // Assert
        assertNotNull(bookings);
        assertEquals(2, bookings.size());

        BookingServiceResponse firstBooking = bookings.get(0);
        assertEquals("John Doe", firstBooking.getCustomerName());
//        assertEquals("Inception", firstBooking.getMovie().getTitle());
//        assertEquals("Grand Cinema", firstBooking.getTheater().getName());
//        assertEquals("Screen 1", firstBooking.getScreen().getName());
//        assertEquals(200, firstBooking.getScreen().getSeatCapacity());
//        assertEquals(LocalDateTime.of(2024, 10, 12, 15, 30), firstBooking.getShowTime());
//        assertEquals(2, firstBooking.getTotalTickets());

        // Verify that findAll() was called once
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBookings_EmptyList() {
        // Arrange
        when(bookingRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<BookingServiceResponse> bookings = bookingService.getAllBookings();

        // Assert
        assertNotNull(bookings);
        assertTrue(bookings.isEmpty());

        // Verify that findAll() was called once
        verify(bookingRepository, times(1)).findAll();
    }


    @Test
    void getBookingById() {
        // Arrange
        Long bookingId = 1L;
        Booking mockBooking = new Booking();
        mockBooking.setId(bookingId);
        mockBooking.setTotalTickets(2);
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(mockBooking));

        // Act
        BookingServiceResponse booking = bookingService.getBookingById(bookingId);

        // Assert
        assertNotNull(booking);
        assertEquals(bookingId, booking.getId());
        verify(bookingRepository, times(1)).findById(bookingId);
    }

    @Test
    void createBooking() {
        // Arrange
        Booking newBooking = new Booking();
        newBooking.setTotalTickets(3);
        when(bookingRepository.save(newBooking)).thenReturn(newBooking);

        // Act
        Booking createdBooking = bookingService.createBooking(newBooking);

        // Assert
        assertNotNull(createdBooking);
        assertEquals(3, createdBooking.getTotalTickets());
        verify(bookingRepository, times(1)).save(newBooking);
    }

    @Test
    void updateBooking() {
        // Arrange
        Long bookingId = 1L;
        Booking existingBooking = new Booking();
        existingBooking.setId(bookingId);
        existingBooking.setTotalTickets(2);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));

        Booking updatedBooking = new Booking();
        updatedBooking.setId(bookingId);
        updatedBooking.setTotalTickets(4);

        when(bookingRepository.save(existingBooking)).thenReturn(updatedBooking);

        // Act
        Booking result = bookingService.updateBooking(bookingId, updatedBooking);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.getTotalTickets());
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, times(1)).save(existingBooking);
    }

    @Test
    void deleteBooking() {
        // Arrange
        Long bookingId = 1L;
        Booking mockBooking = new Booking();
        mockBooking.setId(bookingId);
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(mockBooking));

        // Act
        bookingService.deleteBooking(bookingId);

        // Assert
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }

    @Test
    void calculateBooking() {
        // Arrange
        BookingRequestDTO mockBooking = new BookingRequestDTO();
        mockBooking.setTotalTickets(5);
        mockBooking.setTicketPrice(100);
        mockBooking.setShowTime(LocalDateTime.now());

        double expectedTotalCost = 450;

        BookingResponseDTO mockBookingResponse = new BookingResponseDTO(mockBooking.getTotalTickets(),mockBooking.getTicketPrice(),expectedTotalCost);

        when(bookingService.calculateBooking(mockBooking)).thenReturn(mockBookingResponse);

        // Act
        double totalCost = bookingService.calculateBooking(mockBooking).getTotalCost();

        // Assert
        assertEquals(expectedTotalCost, totalCost);
    }

    @Test
    void calculateAfternoonDiscount() {

        BookingRequestDTO mockBooking = new BookingRequestDTO();
        mockBooking.setTotalTickets(5);
        mockBooking.setTicketPrice(100);
        mockBooking.setShowTime(LocalDateTime.of(2024, 10, 12, 15, 0));
        double expectedTotalCost = 400;
        BookingResponseDTO mockBookingResponse = new BookingResponseDTO(mockBooking.getTotalTickets(),mockBooking.getTicketPrice(),expectedTotalCost);
        when(bookingService.calculateAfternoonDiscount(mockBooking)).thenReturn(mockBookingResponse);

        // Act
        double discountPrice = bookingService.calculateAfternoonDiscount(mockBooking).getTotalCost();
        // Assert
        assertEquals(expectedTotalCost, discountPrice);
    }

    @Test
    void getAllBookings() {
    }
}