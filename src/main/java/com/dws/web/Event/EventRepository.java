package com.dws.web.Event;

import com.dws.web.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.CachingUserDetailsService;

import java.util.List;


public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findByCategory(String category);
    List<Event> findByCustomers(Customer customer);

}


