package org.example.bookingplatform.service;

import org.example.bookingplatform.Entity.Movie;
import org.example.bookingplatform.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieServiceImpl implements IMovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    existingMovie.setTitle(movie.getTitle());
                    existingMovie.setLanguage(movie.getLanguage());
                    existingMovie.setGenre(movie.getGenre());
                    existingMovie.setDescription(movie.getDescription());
                    existingMovie.setReleaseDate(movie.getReleaseDate());
                    return movieRepository.save(existingMovie);
                }).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
