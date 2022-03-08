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

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("event", eventHolder.getEvents());
        return "index";
    }

    @GetMapping("/index/leisure")
    public String leisure(Model model) {
        return "leisure_template";
    }

    @GetMapping("/index/restaurants")
    public String restaurants(Model model) {
        return "restaurants_template";
    }

    @GetMapping("/index/tourism")
    public String tourism(Model model) {
        return "tourism_template";
    }


    @DeleteMapping("/events/delete")
    public String deleteEvent(@RequestBody Event e) {
        long id=eventHolder.getEvent(e);
        eventHolder.deleteEvent(id);
        return "deleted_event";
    }

    @PostMapping("/events/new")
    public String newEvent(@RequestBody Event e) {
        eventHolder.addEvent(e);
        return "saved_event";
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
            return "catalogue";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/events")
    public String catalogue(Model model) {
        model.addAttribute("events", eventHolder.getEvents());
        return "catalogue";
    }

}
