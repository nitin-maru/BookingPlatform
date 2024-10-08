package org.example.bookingplatform.controller;

import org.example.bookingplatform.Dtos.TheatreDTO;
import org.example.bookingplatform.Entity.Theater;
import org.example.bookingplatform.service.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    @Autowired
    private TheatreServiceImpl theaterService;


    @GetMapping("/movie/{movieId}/town/{town}/date/{date}")
    public ResponseEntity<List<TheatreDTO>> getTheatresByMovieTownAndDate(
            @PathVariable Long movieId,
            @PathVariable String town,
            @PathVariable String date
    ) {
        LocalDate selectedDate = LocalDate.parse(date);  // Convert the string date to LocalDate
        List<TheatreDTO> theatres = theaterService.getTheatresByMovieTownAndDate(movieId, town, selectedDate);
        return ResponseEntity.ok(theatres);
    }


    @GetMapping
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        return theaterService.getTheaterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Theater createTheater(@RequestBody Theater theater) {
        return theaterService.createTheater(theater);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody Theater theater) {
        return theaterService.updateTheater(id, theater) != null ?
                ResponseEntity.ok(theater) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }
}
