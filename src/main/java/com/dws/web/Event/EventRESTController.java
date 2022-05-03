package com.dws.web.Event;

import com.dws.web.Customer.Customer;
import com.dws.web.Review.Review;
import com.dws.web.Review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/admin/events/new")
    public ResponseEntity<Event> newEventAPI(@RequestBody Event event) {
        eventService.addEvent(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/events/{idEvent}/delete")
    public ResponseEntity<Event> deleteEventAPI(@PathVariable long idEvent) {
        boolean e= eventService.deleteEvent(idEvent);
        if (e) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/admin/events/{idEvent}/update")
    public ResponseEntity<Event> updateEventAPI(@PathVariable long idEvent, @RequestBody Event updatedEvent) {
        Event event = eventService.getEvent(idEvent);
        if (event != null) {
            updatedEvent.setIdEvent(event.getIdEvent());
            this.eventService.addUpdatedEvent(updatedEvent);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/events")
    public ResponseEntity<Collection> getAllEventAPI() {
        Collection<Event> events = eventService.getEvents();
        if (events != null) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events/filtered")
    public ResponseEntity<Collection> filterBy(float priceMin, float priceMax){

        Collection<Event> l = this.eventService.filterEvents(priceMin,priceMax);
        return new ResponseEntity<>(l, HttpStatus.CREATED);
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

    @GetMapping("/admin/events/category/{category}")
    public ResponseEntity<Collection> getEventsFilteredByCategoryAPI(@PathVariable String category) {
        if (category.equalsIgnoreCase("ocio")||category.equalsIgnoreCase("restaurante")||category.equalsIgnoreCase("turismo")) {
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


    @PostMapping("/event/{idEvent}/review/filtered")
    public ResponseEntity<Collection> filterReview(@PathVariable long idEvent, String userName){

        Event event = eventService.getEvent(idEvent);
        Collection<Review> c = this.reviewService.filterReview(event, userName);

        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }


    @DeleteMapping("/event/{idEvent}/review/delete/{idReview}")
    public ResponseEntity<Review> deleteReviewAPI(@PathVariable long idEvent, @PathVariable long idReview) {
        Event e = eventService.getEvent(idEvent);
        Review r=e.getReview(idReview);
        r=reviewService.deleteReviewFromAnEvent(e, r);
        if (r != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/event/{idEvent}/review/update/{idReview}")
    public ResponseEntity<Review> updateReviewAPI(@PathVariable long idEvent, @PathVariable long idReview, @RequestBody Review updatedReview, Authentication auth) {
        Event e = eventService.getEvent(idEvent);
        Review r=e.getReview(idReview);
        var sec= SecurityContextHolder.getContext().getAuthentication();
        updatedReview.setUserName(sec.getName());
        if (r != null) {
            reviewService.addUpdatedReviewToThisEvent(idReview, updatedReview);
            return new ResponseEntity<>(reviewService.getReview(e, idReview), HttpStatus.OK);
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
