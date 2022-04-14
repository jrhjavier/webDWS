package com.dws.web.Event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EventRepository extends JpaRepository<Event,Long> {

    Optional<Event> findByName(String name);
    List<Event> findByCategory(String category);
}


