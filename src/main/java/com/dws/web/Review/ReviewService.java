package com.dws.web.Review;

import com.dws.web.Event.Event;
import com.dws.web.Event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    EventRepository eventRepository;

    public ReviewService(){
    }

    public void addReviewToThisEvent(Event e, Review r){ //Igual no se modifica el id de review
        Optional<Event> e1=eventRepository.findById(e.getId());
        if (e1.isPresent()){
            reviewRepository.save(r);
        }
    }

    public void deleteReviewFromAnEvent(Event e, Review r){
        Optional<Event> e1=eventRepository.findById(e.getId());
        if (e1.isPresent()){
            Event e2=e1.get();
            reviewRepository.delete(r);
        }
    }

    public Collection<Review> getAllReviewsOfAnEvent(Event e){
        Optional<Event> e1=eventRepository.findById(e.getId());
        if (e1.isPresent()){
            return e1.get().getAllReviews();
        }
        else{
            return null;
        }
    }

    public Review getReview(Event e, long idReview){
        Optional<Review> r=this.reviewRepository.findById(idReview);
        if(r.isPresent()){
            return r.get();
        }
        else{
            return null;
        }
    }

}
