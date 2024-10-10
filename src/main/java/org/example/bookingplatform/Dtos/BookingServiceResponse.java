package org.example.bookingplatform.Dtos;

public class BookingServiceResponse {
    private Long id; // Booking ID
    private String bookingDate;
    private Long customerId; // Customer ID
    private String customerName; // Customer name
    private String customerEmail; // Customer email

    // Constructor, Getters, and Setters
    public BookingServiceResponse(Long id, String bookingDate, Long customerId, String customerName, String customerEmail) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
