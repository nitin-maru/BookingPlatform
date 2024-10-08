package org.example.bookingplatform.Dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowTimeDTO {

    private String screenName;
    private LocalDateTime showTime;

    public ShowTimeDTO(String screenName, LocalDateTime showTime) {
        this.screenName = screenName;
        this.showTime = showTime;
    }

    // Getters and Setters
}
