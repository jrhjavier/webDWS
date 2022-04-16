package com.dws.web.Customer;

import com.dws.web.Event.Event;
import com.dws.web.Event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EventService eventService;

    //CUSTOMER

    @PostMapping("/customer/new")
    public String newCustomer(Model model, Customer c) {
        customerService.addClient(c);
        model.addAttribute("customer",c);
        return "savedCustomer";
    }

    @DeleteMapping("/customer/delete")
    public String deleteCustomer(String email) {
        Customer c=customerService.getCustomer(email);
        customerService.deleteCustomer(c.getIdClient());
        return "deletedCustomer";
    }

    @PutMapping("/customer/update")
    public String updateCustomer(Model model, String email, Customer updatedCustomer) {
        Customer c=customerService.getCustomer(email);
        if (c != null) {
            long id=c.getIdClient();
            customerService.deleteCustomer(id);
            updatedCustomer.setIdClient(id);
            updatedCustomer.setPassword();
            customerService.addUpdatedClient(updatedCustomer);
            model.addAttribute("customer", c);
            return "savedCustomer";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/customer")
    public String getACustomer(Model model, @RequestParam String email) {
        Customer c=customerService.getCustomer(email);
        model.addAttribute("customer", c);
        return "planning";
    }

    //PLANNING

    @GetMapping("/planning/new/{name}")
    public String newEvent2(Model model, @PathVariable String name) {
        Customer c= customerService.getCustomer("admin");
        Event e = eventService.getEventByName(name);
        if (c.addToPlanning(e)){
            model.addAttribute("event",e);
            return "addedEvent";
        }
        else{
            return "duplicatedEvent";
        }
    }

    @GetMapping("/planning/delete/{idEvent}")
    public String deleteEventFromPlanning(Model model, @PathVariable long idEvent) {
        Customer c=customerService.getCustomer("admin");
        Event e=eventService.getEvent(idEvent);
        customerService.deleteEventFromPlanning(c, idEvent);
        model.addAttribute("event", e);
        return "deletedEventFromPlanning";
    }

    /*
    @PutMapping("/planning/update")
    public String updateEvent(@RequestBody Customer c, @RequestParam long idOldEvent, @RequestBody Event updatedEvent) {
        customerHolder.updateAnEvent(c, idOldEvent, updatedEvent);
        return "addedEvent";
    }

     */

    @PostMapping("/planning/new")
    public String addEventToPlanningAPI(Model model, String email, Event e){
        Customer c= customerService.getCustomer(email);
        if (c.addToPlanning(e)){
            model.addAttribute("event",e);
            return "addedEvent";
        }
        else{
            return "duplicatedEvent";
        }
    }

    @GetMapping("/planning")
    public String planning(Model model) {
        Customer c= customerService.getCustomer("admin");
        model.addAttribute("events", customerService.getAllEventsOfACustomer(c));
        return "planning";
    }

    @GetMapping("/planning/{category}")
    public String catalogueFilteredByCategory(Model model, @RequestBody Customer c, @PathVariable String category) {
        if (category.equalsIgnoreCase("ocio")||category.equalsIgnoreCase("restauracion")||category.equalsIgnoreCase("turismo")) {
            model.addAttribute("events", eventService.getEventsFilteredByCategory(category));
            return "planning";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
