package org.example.bookingplatform.service;

import org.example.bookingplatform.Dtos.ShowTimeDTO;
import org.example.bookingplatform.Dtos.TheatreDTO;
import org.example.bookingplatform.Entity.Movie;
import org.example.bookingplatform.Entity.Theater;
import org.example.bookingplatform.repository.MovieRepository;
import org.example.bookingplatform.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TheatreServiceImpl implements ITheaterService {
    private final TheaterRepository theatreRepository;
    private final MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public TheatreServiceImpl(TheaterRepository theatreRepository, MovieRepository movieRepository) {
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    @Override
    public Optional<Theater> getTheaterById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }


    @Override
    public Theater updateTheater(Long id, Theater theater) {
        return theaterRepository.findById(id)
                .map(existingTheater -> {
                    existingTheater.setName(theater.getName());
                    existingTheater.setLocation(theater.getLocation());
                    existingTheater.setScreens(theater.getScreens());
                    return theaterRepository.save(existingTheater);
                }).orElseThrow(() -> new RuntimeException("Theater not found"));
    }

    @Override
    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }

    public List<TheatreDTO> getTheatresByMovieTownAndDate(Long movieId, String town, LocalDate date) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));

        // Fetch theatres showing the movie in the specified town and date
        List<Theater> theatres = theatreRepository.findByMovieAndTownAndDate(movieId, town, date);

        // Convert theatres to DTO with showtimes
        return theatres.stream()
                .map(theatre -> new TheatreDTO(
                        theatre.getId(),
                        theatre.getName(),
                        theatre.getLocation(),
                        getShowtimes(theatre, movieId, date)))
                .collect(Collectors.toList());
    }

    private List<ShowTimeDTO> getShowtimes(Theater theatre, Long movieId, LocalDate date) {
        // Fetch showtimes for a specific movie in the theatre on the given date
        return theatre.getScreens().stream()
                .flatMap(screen -> screen.getShowtimes().stream())
                .filter(showtime -> showtime.getMovie().getId().equals(movieId)
                        && showtime.getShowTime().toLocalDate().equals(date))
                .map(showtime -> new ShowTimeDTO(
                        showtime.getScreen().getName(),
                        showtime.getShowTime()))
                .collect(Collectors.toList());
    }
}
