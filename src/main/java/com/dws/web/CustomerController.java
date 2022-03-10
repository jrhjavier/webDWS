package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

public class CustomerController {

    @Autowired
    CustomerHolder customerHolder;

    @PostMapping("/planning/new")  //Add an event to the planning
    public String addEventToPlanningAPI(@RequestBody Customer c, @RequestBody Event e){
            customerHolder.addEventToPlanning(c, e);
            return "saved_event";
    }

    @DeleteMapping("/planning/delete")
    public String deleteEvent(@RequestBody Customer c, @RequestBody long idEvent) {
        customerHolder.deleteEventFromPlanning(c, idEvent);
        return "deleted_event";
    }

    @PutMapping("/planning/update")
    public String updateEvent(@RequestBody Customer c, @RequestParam long idOldEvent, @RequestBody Event updatedEvent) {
        customerHolder.updateAnEvent(c, idOldEvent, updatedEvent);
        return "saved_event";
    }

    @GetMapping("/planning")
    public String planning(Model model, @RequestBody Customer c) {
        model.addAttribute("events", customerHolder.getAllEventsOfACustomer(c));
        return "planning";
    }

    @GetMapping("/planning/{category}")
    public String catalogueFilteredByCategory(Model model, @RequestBody Customer c, @PathVariable String category) {
        if (category.equalsIgnoreCase("ocio")||category.equalsIgnoreCase("restauracion")||category.equalsIgnoreCase("turismo")) {
            model.addAttribute("events", customerHolder.getEventsOfACategory(c, category));
            return "planning";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }





}
