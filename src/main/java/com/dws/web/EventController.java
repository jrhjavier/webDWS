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

    //EVENT (Revisar)

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

    @DeleteMapping("/events/delete")
    public String deleteEvent(Event e) {
        long id=eventHolder.getEvent(e);
        eventHolder.deleteEvent(id);
        return "deleted_event";
    }

    @PutMapping("/events/update")
    public String updateEvent(Model model, long id, Event updatedEvent) {
        Event event = eventHolder.getEvent(id);
        if (event != null) {
            updatedEvent.setIdEvent(id);
            eventHolder.addEvent(updatedEvent);
            model.addAttribute("event",updatedEvent);
            return "savedEvent";
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

    @GetMapping("/events/{idEvent}")
    public String getAnEvent(Model model, @PathVariable long idEvent) {
        model.addAttribute("event", eventHolder.getEvent(idEvent));
        return "event";
    }

    //REVIEW

    @PostMapping("/event/review/new")
    public String newReview(Model model, long idEvent, Review r) {
        Event e=eventHolder.getEvent(idEvent);
        e.addReviewToThisEvent(r);
        model.addAttribute("review",r);
        return "savedReview";
    }

    @DeleteMapping("/event/review/delete")
    public String deleteReview(long idEvent, long idReview) {
        Event e=eventHolder.getEvent(idEvent);
        e.deleteReviewOfThisEvent(idReview);
        return "deleted_review";
    }

    @PutMapping("/event/review/update")
    public String updateReview(Model model, long idEvent, long idOldReview, Review updatedReview) {
        Event e = eventHolder.getEvent(idEvent);
        Review r=e.getReview(idOldReview);
        if (r != null) {
            e.deleteReviewOfThisEvent(idOldReview);
            updatedReview.setIdReview(idOldReview);
            e.addUpdatedReviewToThisEvent(updatedReview);
            model.addAttribute("review",updatedReview);
            return "savedReview";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/event/{idEvent}/reviews")
    public String getAllReviewsOfAnEvent(Model model, @PathVariable long idEvent) {
        Event e=eventHolder.getEvent(idEvent);
        model.addAttribute("reviews", e.getAllReviews());
        return "reviews";
    }

    @GetMapping("/event/{idEvent}/{idReview}")
    public String getAReview(Model model, @PathVariable long idEvent, @PathVariable long idReview) {
        Event e=eventHolder.getEvent(idEvent);
        model.addAttribute("review", e.getReview(idReview));
        return "review";
    }

}
