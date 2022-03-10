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

    /*@GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("event", eventHolder.getEvents());
        return "index_template";
    }
*/
    @GetMapping("/leisure")
    public String leisure(Model model) {
        return "leisure_template";
    }

    @GetMapping("/restaurants")
    public String restaurants(Model model) {
        return "restaurants_template";
    }

    @GetMapping("/tourism")
    public String tourism(Model model) {
        return "tourism_template";
    }

    @GetMapping("/addEvent")
    public String addEvent(Model model) {
        return "newEvent";
    }

    @DeleteMapping("/events/delete")
    public String deleteEvent(@RequestBody Event e) {
        long id=eventHolder.getEvent(e);
        eventHolder.deleteEvent(id);
        return "deleted_event";
    }

    @PostMapping("/events/new")
    public String newEvent(Model model, Event e) {
        //Event e= new Event()
        eventHolder.addEvent(e);
        model.addAttribute("event",e);
        return "savedEvent";
    }


    @GetMapping("/events/new")
    public String newEvent(@RequestParam Event e) {
        eventHolder.addEvent(e);
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


    @GetMapping("/events/reviews/{id}")
    public String showReviews(Model model, @PathVariable long id) {
        Event e=eventHolder.getEvent(id);
        model.addAttribute("reviews", eventHolder.getReviewsOfAnEvent(e));
        return "show_reviews";
    }

    @PostMapping("/events/{id}/review/add")
    public String newReview(@PathVariable long id, @RequestBody Review r){
        Event e=eventHolder.getEvent(id);
        eventHolder.addReview(e, r);
        return "added_review";
    }

}
