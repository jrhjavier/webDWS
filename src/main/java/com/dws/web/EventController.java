package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class EventController {

    @Autowired
    EventHolder eventHolder;


    @DeleteMapping("/events/delete")
    public String deleteEvent(@RequestBody Event e) {
        long id=eventHolder.getEvent(e);
        eventHolder.deleteEvent(id);
        return "deleted_event";
    }

    @PostMapping("/events/new")
    public String newEvent(Model model, Event e) {
        eventHolder.addEvent(e);
        model.addAttribute("event",e);
        return "savedEvent";
    }


    @GetMapping("/events/new")
    public String newEvent2(Model model, @RequestParam String name,@RequestParam String category, @RequestParam String description, @RequestParam int price) {
        Event e= new Event(name,category,description,price);
        eventHolder.addEvent(e);
        model.addAttribute("event",e);
        return "savedEvent";
    }



    @PutMapping("/events/update")
    public String updateEvent(@RequestParam long id, @RequestBody Event updatedEvent) {
        Event event = eventHolder.getEvent(id);
        if (event != null) {
            updatedEvent.setIdEvent(id);
            eventHolder.addEvent(updatedEvent);
            return "updated_event";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events/{category}")
    public String catalogueFilteredByCategory(Model model, @PathVariable String category) {
        if (category.equalsIgnoreCase("ocio")||category.equalsIgnoreCase("restauracion")||category.equalsIgnoreCase("turismo")) {
            model.addAttribute("events", eventHolder.getEventsFilteredByCategory(category));
            return "events";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/events")
    public String catalogue(Model model) {
        model.addAttribute("events", eventHolder.getEvents());
        return "events";
    }

}
