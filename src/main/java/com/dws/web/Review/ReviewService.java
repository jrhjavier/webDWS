package com.dws.web.Review;

import com.dws.web.Event.Event;
import com.dws.web.Event.EventRepository;
import lombok.NoArgsConstructor;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    EventRepository eventRepository;


    public void addReviewToThisEvent(Event e, Review r){ //Igual no se modifica el id de review
        r.assignEvent(e);
        e.addReviewToThisEvent(r);
        this.reviewRepository.save(r);
    }

    public void deleteReviewFromAnEvent(Event e, Review r){
        e.deleteReviewOfThisEvent(r.getIdReview());
        reviewRepository.delete(r);
    }

    public void addUpdatedReviewToThisEvent(Event e, Review r){
        e.addUpdatedReviewToThisEvent(r);
        r.assignEvent(e);
        reviewRepository.save(r);
    }

    public Collection<Review> getAllReviewsOfAnEvent(Event e){
        return this.reviewRepository.findByEvent(e);
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

    public List<Review> getAllReviews(){
        return this.reviewRepository.findAll();
    }

}