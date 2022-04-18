package com.dws.web.Event;

import com.dws.web.Customer.Customer;
import com.dws.web.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EventRepository extends JpaRepository<Event,Long> {

    Optional<Event> findByName(String name);
    List<Event> findByCategory(String category);
    List<Event> findByCustomer(Customer c);

}


