package org.example.bookingplatform.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;
    private String genre;
    private String description;  // Corrected typo
    private LocalDateTime releaseDate;

    // Getters and Setters
}


