package org.example.bookingplatform.repository;

import org.example.bookingplatform.Entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    @Query("SELECT t FROM Theater t JOIN t.screens s JOIN s.showtimes st " +
            "WHERE st.movie.id = :movieId " +
            "AND t.location = :town " +
            "AND DATE(st.showTime) = :date")
    List<Theater> findByMovieAndTownAndDate(@Param("movieId") Long movieId, @Param("town") String town, @Param("date") LocalDate date);
}
