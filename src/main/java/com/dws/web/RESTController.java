package com.dws.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api")
@RestController
public class RESTController {
    @Autowired
    EventHolder eventHolder;

    @GetMapping("/events")  //All events
    public Collection<Event> getEventsAPI(){
        return eventHolder.getEvents();
    }

    @GetMapping("/events/category/{category}")  //Events by category
    public Collection<Event> getEventsByCategoryAPI(@PathVariable String category){
        return eventHolder.getEventsFilteredByCategory(category);
    }

    @GetMapping("/events/{id}")  //Products by id
    public ResponseEntity<Event> getEventAPI(@PathVariable long id){
        return new ResponseEntity<>(eventHolder.getEvent(id), HttpStatus.OK);
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


}