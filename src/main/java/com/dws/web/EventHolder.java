package com.dws.web;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventHolder {

    private Map<Long, Event> events = new ConcurrentHashMap<>();
    private AtomicLong lastID = new AtomicLong();

    private AtomicLong lastIDReview = new AtomicLong();


    public void addEvent (Event event){
        long id = this.lastID.incrementAndGet();
        event.setIdEvent(id);
        this.events.put(id, event);
    }

    public Collection<Event> getEvents(){
        return this.events.values();
    }

    public Event getEvent(long id){
        return this.events.get(id);
    } //Por id

    public Long getEvent(Event e){
        return e.getId();
    } //Por evento

    public Event deleteEvent(long id){
        Event event = this.events.get(id);
        this.events.remove(id);
        return event;  //el return para que?
    }

    public AtomicLong getLastID() {
        return this.lastID;
    }

    public Collection<Event> getEventsFilteredByCategory(String category){  //eventos filtrados por categoria
        Collection<Event> l=null;
        for (Event e : this.events.values()){
            if (e.sameCategory(category)){
                l.add(e);
            }
        }
        return l;
    }

    public void addReview(Event e, Review r){
        long id = r.getLastID().incrementAndGet();
        r.setIdReview(id);
        e.setReview(r);
    }

    public Collection<Review> getAllReviews(Event e) {
        return e.getReviews();
    }

    public void deleteReview(Event e, Review r1) {
        e.eventContainsReview(r1);
        if (e != null) {
            e.deleteReviewOfAnEvent(r1);
        }
    }

    public void cleanReviews(Event e) {
        e.cleanReviews();
    }


    public boolean containsReview(Event e, Review r1) {
        e.eventContainsReview(r1);
        return r1!=null;
    }

    private Review inReviews(Event e, Review r1) {

        e.eventContainsReview(r1);

        if (e!=null){
            return r1;
        }
        else{
            return null;
        }
    }

    public Review getReview(Event e, long id){
        Review r=e.getReview(id);
        return e.getReview(r);
    }


    public Collection<Review> getReviewsOfAnEvent(Event e){
        Collection<Review> l=null;

        for (Review r : e.getReviews()){
            if (r.reviewOfAnEvent(e)){
                l.add(r);
            }
        }
        return l;
    }
/*
    public Collection<Review> getReviewsOfAnEventCategory(String category){
        Collection<Event> eventsFilteredByCategory=this.getEventsFilteredByCategory(category);


        for (Event e : ){
            if (r.reviewOfAnEvent(e)){
                l.add(r);
            }
        }
        return l;
    }


 */
    public Collection<Review> getReviewsOfAClient(Event e, Customer c){

        Collection<Review> l= null;

        for (Review r : e.getReviews()){
            if (r.reviewOfAClient(c)){
                l.add(r);
            }
        }
        return l;
    }

    public int eventStarsAverage(Event e){

        Collection<Review> reviewsOfAnEvent=this.getReviewsOfAnEvent(e);

        int suma=0;

        for (Review r : reviewsOfAnEvent){
            suma+=r.getStars();
        }

        return suma/reviewsOfAnEvent.size();
    }


}