package org.example.bookingplatform.repository;

import org.example.bookingplatform.Entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    // Custom query methods can be added here if needed
}
