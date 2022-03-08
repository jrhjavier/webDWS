package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EventController {


    @Autowired
    EventHolder eventHolder;

    @PostMapping("/event/new")
    public String newEvent(@RequestBody Event e) {
        eventHolder.addEvent(e);
        return "saved_event";
    }


    @GetMapping("/events/{category}")
    public String catalogueFilteredByCategory(Model model, @PathVariable String category, HttpServletRequest request) {
        if (category.equalsIgnoreCase("ocio")||category.equalsIgnoreCase("restauracion")||category.equalsIgnoreCase("turismo")) {
            model.addAttribute("logged", request.isUserInRole("USER"));
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("events", eventHolder.getEventsFilteredByCategory(category));
            return "catalogue";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("logged", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("event", eventHolder.getEvents());
        return "index";
    }

    @GetMapping("/events")
    public String catalogue(Model model, HttpServletRequest request) {
        model.addAttribute("logged", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("events", eventHolder.getEvents());
        return "events";
    }

}
