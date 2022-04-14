package com.dws.web.Review;

import com.dws.web.Event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public ReviewService(){
    }

    public void addReviewToThisEvent(Event e, Review r){

        e.addReviewToThisEvent(r);
    }

}
