package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CustomerController {

    @Autowired
    CustomerHolder customerHolder;

    @Autowired
    EventHolder eventHolder;

    //CUSTOMER

    @PostMapping("/customer/new")
    public String newCustomer(Model model, Customer c) {
        customerHolder.addClient(c);
        model.addAttribute("customer",c);
        return "savedCustomer";
    }

    @DeleteMapping("/customer/delete")
    public String deleteCustomer(String email) {
        Customer c=customerHolder.getCustomer(email);
        customerHolder.deleteCustomer(c.getIdClient());
        return "deleted_customer";
    }

    @PutMapping("/customer/update")
    public String updateCustomer(Model model, String email, Customer updatedCustomer) {
        Customer c=customerHolder.getCustomer(email);
        if (c != null) {
            long id=c.getIdClient();
            customerHolder.deleteCustomer(id);
            updatedCustomer.setIdClient(id);
            customerHolder.addUpdatedClient(updatedCustomer);
            model.addAttribute("customer", c);
            return "savedCustomer";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerHolder.getAllCustomers());
        return "customers";
    }

    @GetMapping("/customer/{email}")
    public String getACustomer(Model model, @PathVariable String email) {
        Customer c=customerHolder.getCustomer(email);
        model.addAttribute("customer", c);
        return "customer";
    }

    //PLANNING (Revisar)

    @GetMapping("/planning/new/{name}")
    public String newEvent2(Model model, @PathVariable String name) {
        Customer c= customerHolder.getCustomer("admin");
        Event e = eventHolder.getEventByName(name);
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
        return "savedEvent";
    }

    @PostMapping("/planning/new")
    public String addEventToPlanningAPI(Model model,Event e){
        //Customer c= new Customer(email);
        Customer c= customerHolder.getCustomer("admin");
        customerHolder.addEventToPlanning(c, e);
        model.addAttribute("event",e);
        return "savedEvent";
    }

    @GetMapping("/planning")
    public String planning(Model model) {
        Customer c= customerHolder.getCustomer("admin");
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
