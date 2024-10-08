package org.example.bookingplatform.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "screen")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theater theater;

    private Integer seatCapacity;

    // Add a OneToMany relationship with ShowTime
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private List<ShowTime> showtimes;
    // Getters and Setters
}
