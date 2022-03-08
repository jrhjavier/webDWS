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

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id) {
        Event event = eventHolder.getEvent(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(event, HttpStatus.NOT_FOUND);
        }
    }

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

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Event> borrarEvent(@PathVariable long id) {
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

    @PostMapping("/events/")
    public ResponseEntity<Event> nuevoEvent(@RequestBody Event event) {
        long id = eventHolder.getLastID().incrementAndGet();
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

    /*@PostMapping("/events/{id}")  //Add an event to the planning
    public Collection<Event> addEventToPlanningAPI(@PathVariable long id){
        Event e = eventHolder.getEvent(id);
        return Customer.addPlanning();  //no se como hacer esto
    }*/



    /*@PostMapping("/sitio")
    public ResponseEntity<Event> create(@RequestBody Event event){
        this.eventHolder.addEvent(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }*/

       /* @PostMapping("/events/{id}") //Adding item by id to cart and returning products in cart, not units
    public Collection<Event> addToPlanningAPI(@PathVariable long id){
        Event e = eventHolder.getEvent(id);
        product_Service.reduceStock(id, 1);
        lCustomer.addToCart(p1, 1);
        return lCustomer.getCart().keySet();
    }
*/

}
