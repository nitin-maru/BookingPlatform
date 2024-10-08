package org.example.bookingplatform.controller;

import org.example.bookingplatform.Entity.Movie;
import org.example.bookingplatform.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private IMovieService movieService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/movies")
    public List<Movie> getAllMoviesForAdmin() {
        // Only admins can access this method
        return movieService.getAllMovies();
    }
}
