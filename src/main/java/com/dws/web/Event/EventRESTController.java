package com.dws.web.Event;

import com.dws.web.Review.Review;
import com.dws.web.Review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RequestMapping("/api")
@RestController
public class EventRESTController {

    @Autowired
    EventService eventService;

    @Autowired
    ReviewService reviewService;

    //EVENT

    @PostMapping("/events/new")
    public ResponseEntity<Event> newEventAPI(@RequestBody Event event) {
        eventService.addEvent(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @DeleteMapping("/events/{idEvent}/delete")
    public ResponseEntity<Event> deleteEventAPI(@PathVariable long idEvent) {
        Event event = eventService.deleteEvent(idEvent);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/events/{idEvent}/update")
    public ResponseEntity<Event> updateEventAPI(@PathVariable long idEvent, @RequestBody Event updatedEvent) {
        Event event = eventService.getEvent(idEvent);
        if (event != null) {
            this.eventService.deleteEvent(idEvent);
            updatedEvent.setIdEvent(idEvent);
            this.eventService.addUpdatedEvent(idEvent, updatedEvent);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events")
    public ResponseEntity<Collection> getAllEventAPI() {
        Collection<Event> events = eventService.getEvents();
        if (events != null) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events/{idEvent}")  //Products by id
    public ResponseEntity<Event> getEventAPI(@PathVariable long idEvent){

        Event e= eventService.getEvent(idEvent);

        if (e!=null){
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/events/category/{category}")
    public ResponseEntity<Collection> eventsFilteredByCategoryAPI(@PathVariable String category) {
        if (category.equalsIgnoreCase("ocio")||category.equalsIgnoreCase("restauracion")||category.equalsIgnoreCase("turismo")) {
            return new ResponseEntity<>(eventService.getEventsFilteredByCategory(category), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //REVIEW

    @PostMapping("/event/{idEvent}/review/new")
    public ResponseEntity<Review> newReviewAPI(@PathVariable long idEvent, @RequestBody Review r) {
        Event e=eventService.getEvent(idEvent);
        reviewService.addReviewToThisEvent(e, r);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @DeleteMapping("/event/{idEvent}/review/delete/{idReview}")
    public ResponseEntity<Review> deleteReviewAPI(@PathVariable long idEvent, @PathVariable long idReview) {
        Event e = eventService.getEvent(idEvent);
        Review r=e.getReview(idReview);
        r=reviewService.deleteReviewFromAnEvent(e, r);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/event/{idEvent}/review/update/{idReview}")
    public ResponseEntity<Review> updateReviewAPI(@PathVariable long idEvent, @PathVariable long idReview, @RequestBody Review updatedReview) {
        Event e = eventService.getEvent(idEvent);
        Review r=e.getReview(idReview);
        if (r != null) {
            this.reviewService.deleteReviewFromAnEvent(e, r);
            updatedReview.setIdReview(idReview);
            e.addUpdatedReviewToThisEvent(updatedReview);
            reviewService.addReviewToThisEvent(e, updatedReview);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/event/{idEvent}/reviews")
    public ResponseEntity<Collection> getAllReviewsOfAnEventAPI(@PathVariable long idEvent) {
        Event e=eventService.getEvent(idEvent);
        Collection<Review> reviews = this.reviewService.getAllReviewsOfAnEvent(e);
        if (reviews != null) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/event/{idEvent}/review/{idReview}")
    public ResponseEntity<Review> getReviewAPI(@PathVariable long idEvent, @PathVariable long idReview){
        Event e= eventService.getEvent(idEvent);
        Review r= e.getReview(idReview);
        if (r!=null){
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
