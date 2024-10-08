package org.example.bookingplatform.service;

import org.example.bookingplatform.Entity.Theater;

import java.util.List;
import java.util.Optional;

public interface ITheaterService {
    List<Theater> getAllTheaters();

    Optional<Theater> getTheaterById(Long id);

    Theater createTheater(Theater theater);

    Theater updateTheater(Long id, Theater theater);

    void deleteTheater(Long id);
}
