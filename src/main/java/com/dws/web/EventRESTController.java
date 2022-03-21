package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RequestMapping("/api")
@RestController
public class EventRESTController {

    @Autowired
    EventHolder eventHolder;

    //EVENT (Revisar)

    @PostMapping("/events")
    public ResponseEntity<Event> newEventAPI(@RequestBody Event event) {
        eventHolder.addEvent(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Event> deleteEventAPI(@PathVariable long id) {
        Event event = eventHolder.deleteEvent(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEventAPI(@PathVariable long id, @RequestBody Event updatedEvent) {
        Event event = eventHolder.getEvent(id);
        if (event != null) {
            updatedEvent.setIdEvent(id);
            eventHolder.addEvent(updatedEvent);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events")
    public ResponseEntity<Collection> getAllEventAPI() {
        Collection<Event> events = eventHolder.getEvents();
        if (events != null) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events/{id}")  //Products by id
    public ResponseEntity<Event> getEventAPI(@PathVariable long id){

        Event e= eventHolder.getEvent(id);

        if (e!=null){
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //REVIEW

    @PostMapping("/event/{idEvent}/review/new")
    public ResponseEntity<Review> newReviewAPI(@PathVariable long idEvent, @RequestBody Review r) {
        Event e=eventHolder.getEvent(idEvent);
        e.addReviewToThisEvent(r);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @DeleteMapping("/event/{idEvent}/review/delete/{idReview}")
    public ResponseEntity<Review> deleteReviewAPI(@PathVariable long idEvent, @PathVariable long idReview) {
        Event e = eventHolder.getEvent(idEvent);
        Review r=e.deleteReviewOfThisEvent(idReview);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/event/{idEvent}/review/update/{idReview}")
    public ResponseEntity<Review> updateReviewAPI(@PathVariable long idEvent, @PathVariable long idReview, @RequestBody Review updatedReview) {
        Event e = eventHolder.getEvent(idEvent);
        Review r=e.getReview(idReview);
        if (r != null) {
            e.deleteReviewOfThisEvent(idReview);
            updatedReview.setIdReview(idReview);
            e.addUpdatedReviewToThisEvent(updatedReview);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/event/{idEvent}/reviews")
    public ResponseEntity<Collection> getAllReviewsOfAnEventAPI(@PathVariable long idEvent) {
        Event e=eventHolder.getEvent(idEvent);
        Collection<Review> reviews = e.getAllReviews();
        if (reviews != null) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/event/{idEvent}/review/{idReview}")
    public ResponseEntity<Review> getReviewAPI(@PathVariable long idEvent, @PathVariable long idReview){
        Event e= eventHolder.getEvent(idEvent);
        Review r= e.getReview(idReview);
        if (r!=null){
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
