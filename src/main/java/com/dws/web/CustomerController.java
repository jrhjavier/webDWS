package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class CustomerController {

    @Autowired
    CustomerHolder customerHolder;



   /* @PostMapping("/planning/new")  //Add an event to the planning
    public String addEventToPlanningAPI(Model model, @RequestParam String email,  @RequestParam String name, @RequestParam String description, @RequestParam String price,@RequestParam String category){
        Customer c= new Customer(email);
        Event e= new Event(name,description,price,category);
        customerHolder.addEventToPlanning(c, e);
        model.addAttribute("event",e);
        return "savedEvent";
    }*/

    @PostMapping("/planning/new")  //Add an event to the planning
    public String addEventToPlanningAPI(Model model, @RequestParam String email,  @RequestParam String name, @RequestParam String category, @RequestParam String description, @RequestParam int price){
        Customer c= new Customer(email);
        Event e= new Event(name,category,description,price);
        customerHolder.addEventToPlanning(c, e);
        model.addAttribute("event",e);
        return "savedEvent";
    }

    @GetMapping("/planning/new/{name}/{category}/{description}/{price}")
    public String newEvent2(Model model, @PathVariable String email, @PathVariable String name,@PathVariable String category, @PathVariable String description, @PathVariable int price) {
        Event e= new Event(name,category,description,price);
        Customer c= new Customer(email);
        customerHolder.addEventToPlanning(c,e);
        model.addAttribute("event",e);
        return "savedEvent";
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
