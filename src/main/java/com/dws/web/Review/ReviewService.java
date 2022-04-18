package com.dws.web.Review;

import com.dws.web.Event.Event;
import com.dws.web.Event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    EventRepository eventRepository;

    public ReviewService(){
    }

    public void addReviewToThisEvent(Event e, Review r){
        e.addReviewToThisEvent(r);
        r.assignEvent(e);
        reviewRepository.save(r);
    }

    public void addUpdatedReviewToThisEvent(Event e, Review r){
        e.addUpdatedReviewToThisEvent(r);
        r.assignEvent(e);
        reviewRepository.save(r);
    }

    public void deleteReviewFromAnEvent(Event e, Review r){
        e.deleteReviewOfThisEvent(r.getIdReview());
        r.unassignEvent(e);
        reviewRepository.delete(r);
    }

    public Collection<Review> getAllReviewsOfAnEvent(Event e){
        return reviewRepository.findByEvent(e);
    }

    public Review getReview(Event e, long idReview){

        Optional<Event> event=eventRepository.findById(e.getId());
        if (event.isPresent()){
            return reviewRepository.getById(idReview);
        }
        else {
            return null;
        }
    }

}
