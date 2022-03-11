package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api")
@RestController
public class CustomerRESTController {

    @Autowired
    CustomerHolder customerHolder;

    @GetMapping("/planning/{email}/{category}")  //Events by category
    public ResponseEntity<Collection> getEventsByCategoryAPI(@PathVariable String email, @PathVariable String category){
        Customer c= customerHolder.getCustomer(email);
        Collection<Event> events =customerHolder.getEventsOfACategory(c, category);
        if (events!=null){
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(events, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/planning/{email}/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable String email, @PathVariable long id) {
        Customer c= customerHolder.getCustomer(email);
        Event event = customerHolder.deleteEventFromPlanning(c, id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/planning/{email}/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String email, @PathVariable long idEvent, @RequestBody Event updatedEvent) {
        Customer c= customerHolder.getCustomer(email);
        Event event = customerHolder.getAnEvent(c, idEvent);
        if (event != null) {
            updatedEvent.setIdEvent(idEvent);
            customerHolder.addEventToPlanning(c, updatedEvent);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/planning/{email}")
    public ResponseEntity<Collection> getAllEventOfACustomer(@PathVariable String email) {
        Customer c=customerHolder.getCustomer(email);
        Collection<Event> events = customerHolder.getAllEventsOfACustomer(c);
        if (!events.isEmpty()) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events/new/{email}")
    public ResponseEntity<Event> newEvent(@PathVariable String email, @RequestBody Event event) {
        Customer c= customerHolder.getCustomer(email);
        long id = customerHolder.getLastIDEvent().incrementAndGet();
        event.setIdEvent(id);
        customerHolder.addEventToPlanning(c, event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("/events/{email}/{id}")  //Products by id
    public ResponseEntity<Event> getEventAPI(@PathVariable String email, @PathVariable long id){

        Customer c= customerHolder.getCustomer(email);
        Event e= customerHolder.getAnEvent(c, id);

        if (e!=null){
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
