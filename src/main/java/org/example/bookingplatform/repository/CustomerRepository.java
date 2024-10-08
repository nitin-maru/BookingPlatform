package org.example.bookingplatform.repository;

import org.example.bookingplatform.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query methods can be added here if needed
}
