package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class EventController {

    @Autowired
    EventHolder eventHolder;

    //EVENT (Revisar)

    @PostMapping("/events/new")
    public String newEvent(Model model, Event e) {
        eventHolder.addEvent(e);
        model.addAttribute("event",e);
        return "addedEvent";
    }

    @GetMapping("/events/new")
    public String newEvent(Model model, @RequestParam String name,@RequestParam String category, @RequestParam String description, @RequestParam int price) {
        Event e= new Event(name,category,description,price);
        eventHolder.addEvent(e);
        model.addAttribute("event",e);
        return "addedEvent";
    }

    @GetMapping("/events/{idEvent}/delete")
    public String deleteEvent(Model model, @PathVariable long idEvent) {
        Event e=eventHolder.deleteEvent(idEvent);
        model.addAttribute("event", e);
        return "deletedEvent";
    }

    @PostMapping("/events/{idEvent}/update")
    public String updateEvent(Model model, @PathVariable long idEvent, Event updatedEvent) {
        Event event = eventHolder.getEvent(idEvent);
        if (event != null) {
            Collection<Review> allReviews= event.getAllReviews();
            eventHolder.deleteEvent(idEvent);
            updatedEvent.setIdEvent(idEvent);

            for(Review r : allReviews ){
                updatedEvent.addReviewToThisEvent(r);
            }

            eventHolder.addUpdatedEvent(updatedEvent);
            model.addAttribute("event",updatedEvent);
            return "updatedEvent";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events/all/restaurants")
    public String getRestaurants(Model model) {
        model.addAttribute("events", eventHolder.getEventsFilteredByCategory("restaurante"));
        return "events";
    }

    @GetMapping("/events/all/leisure")
    public String getLeisure(Model model) {
        model.addAttribute("events", eventHolder.getEventsFilteredByCategory("ocio"));
        return "events";
    }

    @GetMapping("/events/all/tourism")
    public String getTourism(Model model) {
        model.addAttribute("events", eventHolder.getEventsFilteredByCategory("turismo"));
        return "events";
    }

    @GetMapping("/events")
    public String catalogue(Model model) {
        model.addAttribute("events", eventHolder.getEvents());
        return "events";
    }

    @GetMapping("/events/{idEvent}") //Para crear review
    public String getAnEvent(Model model, @PathVariable long idEvent) {
        Event e = eventHolder.getEvent(idEvent);
        model.addAttribute("event", e);
        return "newReview";
    }

    @GetMapping("/events/{idEvent}/modify") //Para modificar evento
    public String getAnEvent2(Model model, @PathVariable long idEvent) {
        Event e = eventHolder.getEvent(idEvent);
        model.addAttribute("event", e);
        return "updateEvent";
    }

    @GetMapping("/events/{idEvent}/review/{idReview}/modify") //Para modificar evento
    public String getAnEvent3(Model model, @PathVariable long idEvent, @PathVariable long idReview) {
        Event e = eventHolder.getEvent(idEvent);
        Review r=e.getReview(idReview);
        model.addAttribute("review", r);
        model.addAttribute("event", e);
        return "updateReview";
    }

    //REVIEW

    @PostMapping("/event/{idEvent}/review/new")
    public String newReview(Model model, @PathVariable long idEvent, Review r) {
        Event e=eventHolder.getEvent(idEvent);
        e.addReviewToThisEvent(r);
        model.addAttribute("reviews", e.getAllReviews());
        model.addAttribute("event", e);
        return "reviews";
    }

    @GetMapping("/event/{idEvent}/review/{idReview}/delete")
    public String deleteReview(Model model, @PathVariable long idEvent, @PathVariable long idReview) {
        Event e=eventHolder.getEvent(idEvent);
        Review r=e.getReview(idReview);
        e.deleteReviewOfThisEvent(idReview);
        model.addAttribute("review", r);
        return "deletedReview";
    }

    @PostMapping("/event/{idEvent}/review/{idReview}/update")
    public String updateReview(Model model,@PathVariable long idEvent,@PathVariable long idReview, Review updatedReview) {
        Event e = eventHolder.getEvent(idEvent);
        Review r = e.getReview(idReview);
        if (r != null) {
            e.deleteReviewOfThisEvent(idReview);
            updatedReview.setIdReview(idReview);
            e.addUpdatedReviewToThisEvent(updatedReview);
            model.addAttribute("event", e);
            model.addAttribute("review",updatedReview);
            return "savedReview";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/event/{idEvent}/reviews")
    public String getAllReviewsOfAnEvent(Model model,@PathVariable long idEvent) {
        Event e=eventHolder.getEvent(idEvent);
        model.addAttribute("reviews", e.getAllReviews());
        model.addAttribute("event", e);
        return "reviews";
    }

    @GetMapping("/event/{idEvent}/{idReview}")
    public String getAReview(Model model, @PathVariable long idEvent, @PathVariable long idReview) {
        Event e=eventHolder.getEvent(idEvent);
        model.addAttribute("review", e.getReview(idReview));
        return "review";
    }

}
