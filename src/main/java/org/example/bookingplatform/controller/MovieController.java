package org.example.bookingplatform.controller;

import org.example.bookingplatform.Entity.Movie;
import org.example.bookingplatform.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie) != null ?
                ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/api/public/movies")
//    public String getPublicMovies() {
//        return "Public Movie List";
//    }
//
//    @GetMapping("/api/private/movies")
//    public String getPrivateMovies() {
//        return "Private Movie List - Only for Authenticated Users";
//    }
}
