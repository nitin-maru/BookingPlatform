package org.example.bookingplatform.repository;

import org.example.bookingplatform.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Custom query methods can be added here if needed
}
