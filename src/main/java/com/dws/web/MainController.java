package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    EventHolder eventHolder;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("logged", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        return "index";
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model, HttpServletRequest request) {
        model.addAttribute("logged", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("productos", eventHolder.getEvents());
        return "catalogue";
    }

    @GetMapping("/catalogue/{category}")
    public String catalogueFilteredByCategory(Model model, @PathVariable String category, HttpServletRequest request) {
        if (category.equals("ocio") || category.equals("restauracion") || category.equals("turismo")) {
            model.addAttribute("logged", request.isUserInRole("USER"));
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("productos", eventHolder.getEventsFilteredByCategory(category));
            return "catalogue";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/events/")
    @ResponseStatus(HttpStatus.CREATED)
    public Event nuevoEvent(@RequestBody Event event) {
        long id = eventHolder.getLastID().incrementAndGet();
        event.setIdEvent(id);
        eventHolder.addEvent(event);
        return event;
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id) {
        Event event = eventHolder.getEvent(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(event, HttpStatus.NOT_FOUND);
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



}