package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class EventRESTController {

    @Autowired
    EventHolder eventHolder;

    /*
    @PostConstruct
    public void init() {
        Event e1= new Event()
    }

     */

    /*
    @GetMapping("/events/{category}")  //Events by category
    public ResponseEntity<Collection> getEventsByCategoryAPI(@PathVariable String category){
        Collection<Event> events =eventHolder.getEventsFilteredByCategory(category);
        if (events!=null){
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(events, HttpStatus.NOT_FOUND);
        }
    }


     */
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable long id) {
        Event event = eventHolder.deleteEvent(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable long id, @RequestBody Event updatedEvent) {
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
    public ResponseEntity<Collection> getAllEvent() {
        Collection<Event> events = eventHolder.getEvents();
        if (events != null) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events/")
    public ResponseEntity<Event> newEvent(@RequestBody Event event) {
        long id = eventHolder.getLastIDEvent().incrementAndGet();
        event.setIdEvent(id);
        eventHolder.addEvent(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
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

    /*
    @GetMapping("/events/{id}/reviews") //Reviews de un Evento
    public ResponseEntity<Collection> getReviewsOfAnEventAPI(@PathVariable long id){
        Event e= eventHolder.getEvent(id);
        Collection<Review> reviews = this.eventHolder.getReviewsOfAnEvent(e);
        if (reviews!=null){
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events/{id}/review/add")
    public ResponseEntity<Review> newReviewAPI(@PathVariable long id, @RequestBody Review r){
        Event e=eventHolder.getEvent(id);
        eventHolder.addReview(e, r);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @DeleteMapping("/events/{id}/review/delete")
    public ResponseEntity<Review> deleteReviewAPI(@PathVariable long idEvent, @RequestBody long idReview){
        Event e=eventHolder.getEvent(idEvent);
        Review r=eventHolder.getReview(e, idReview);
        eventHolder.addReview(e, r);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @PutMapping("/events/{id}/review/update")
    public ResponseEntity<Review> updateReview(@PathVariable long idEvent, @RequestParam long idReview, @RequestBody Review updatedReview) {
        Event event = eventHolder.getEvent(idEvent);
        Review r=eventHolder.getReview(event, idReview);
        if (r != null) {
            updatedReview.setIdReview(idReview);
            eventHolder.addReview(event, updatedReview);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


     */
}
