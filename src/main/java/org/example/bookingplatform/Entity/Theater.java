package org.example.bookingplatform.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Screen> screens;  // Corrected to reference 'Screen', not 'ScreenDto'

    // Add a OneToMany relationship with ShowTime
//    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
//    private List<ShowTime> showtimes;

    private String location;  // Corrected typo

    // Getters and Setters
}
