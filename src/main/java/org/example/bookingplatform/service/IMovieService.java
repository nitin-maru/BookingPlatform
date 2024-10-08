package org.example.bookingplatform.service;

import org.example.bookingplatform.Entity.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<Movie> getAllMovies();

    Optional<Movie> getMovieById(Long id);

    Movie createMovie(Movie movie);

    Movie updateMovie(Long id, Movie movie);

    void deleteMovie(Long id);
}
