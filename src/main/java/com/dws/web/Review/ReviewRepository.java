package com.dws.web.Review;

import com.dws.web.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByEvent(Event event);
}

