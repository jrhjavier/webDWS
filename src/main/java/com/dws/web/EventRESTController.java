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

}
