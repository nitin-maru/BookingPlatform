package org.example.bookingplatform.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class TheatreDTO {

    private Long id;
    private String name;
    private String location;
    private List<ShowTimeDTO> showtimes;

    public TheatreDTO(Long id, String name, String location, List<ShowTimeDTO> showtimes) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.showtimes = showtimes;
    }

    // Getters and Setters
}


