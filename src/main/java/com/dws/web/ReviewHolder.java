package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReviewHolder {

    private Map<Long, Review> reviews = new ConcurrentHashMap<>();
    private AtomicLong lastID = new AtomicLong();

    public void addReview(Review r){
        long id = this.lastID.incrementAndGet();
        r.setIdReview(id);
        this.reviews.put(id, r);
    }

    public Review getReview(long id){
        return this.reviews.get(id);
    }

    public Collection<Review> getAllReviews(){
        return this.reviews.values();
    }

    public List<Review> getReviewsOfAnEvent(Event e){
        List<Review> l=new ArrayList<>();

        for (Review r : this.reviews.values()){
            if (r.reviewOfAnEvent(e)){
                l.add(r);
            }
        }
        return l;
    }

    public Collection<Review> getReviewsOfAClient(Customer c){

        Collection<Review> l=this.reviews.values();

        for (Review r : this.reviews.values()){
            if (r.reviewOfAClient(c)){
                l.add(r);
            }
        }
        return l;
    }

    public int eventStarsAverage(Event e){
        List <Review> reviewsOfAnEvent=this.getReviewsOfAnEvent(e);

        int suma=0;

        for (Review r : reviewsOfAnEvent){
            suma+=r.getStars();
        }

        return suma/reviewsOfAnEvent.size();
    }
}
