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

    @GetMapping("/events")
    public String catalogue(Model model, HttpServletRequest request) {
        model.addAttribute("logged", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("events", eventHolder.getEvents());
        return "events";
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

    @PostMapping("/createEvent/")
    @ResponseStatus(HttpStatus.CREATED)
    public Event newEvent(@RequestBody Event e) {
        eventHolder.addEvent(e);
        return e;
    }









}