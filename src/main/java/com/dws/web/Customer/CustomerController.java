package com.dws.web.Customer;

import com.dws.web.Event.Event;
import com.dws.web.Event.EventService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EventService eventService;

    @Autowired
    CustomerRepository customerRepository;
    private Model model;
    private HttpServletRequest request;

    //CUSTOMER

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        return "login";
    }

    @GetMapping("/loginerror")
    public String loginerror() {
        return "loginerror";
    }

    @GetMapping("/newEvent")
    public String newEvent(HttpServletRequest request) {
        return "newEvent";
    }

    @GetMapping("/customer/newCustomer")
    public String newClient(Model model, HttpServletRequest request) {
        return "newCustomer";
    }

    @GetMapping("/")
    public String privatePage(Model model, HttpServletRequest request) {
        model.addAttribute("username", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        return "index";
    }

    @PostMapping("/customer/new")
    public String newCustomer(Model model, Customer c, HttpServletRequest request) {
        customerService.addClient(c);
        model.addAttribute("customer",c);
        return "savedCustomer";
    }

    @GetMapping("/admin/delete/customer/{email}")
    public String deleteCustomer(@PathVariable String email, Model model) {
        Customer c=customerService.getCustomer(email);
        eventService.removeCustomer(c);
        customerService.deleteCustomer(c.getIdClient());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @PostMapping("/admin/customer/update/{email}")
    public String updateCustomer(Model model, @PathVariable String email, Customer updatedCustomer) {
        Customer c=customerService.getCustomer(email);
        if (c != null) {
            updatedCustomer.setIdClient(c.getIdClient());
            customerService.addUpdatedClient(updatedCustomer);
            model.addAttribute("customer", c);
            return "savedCustomer";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/admin/customer/modify/{email}") //Para modificar evento
    public String modifyAnEvent(Model model, @PathVariable String email) {
        Customer c=customerService.getCustomer(email);
        model.addAttribute("customer", c);
        return "updateCustomer";
    }

    @GetMapping("/admin/customers")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/user/customer")
    public String getACustomer(Model model, @RequestParam String email) {
        Customer c=customerService.getCustomer(email);
        model.addAttribute("customer", c);
        return "planning";
    }

    //PLANNING

    @GetMapping("/user/planning/new/{name}")
    public String newEvent2(Model model, @PathVariable String name, Authentication auth) {
        Customer c= customerService.getCustomer(auth.getName());
        Event e = eventService.getEventByName(name);
        if (customerService.addEventToPlanning(c.getIdClient(),e)){
            eventService.assignCustomer(c,e);
            model.addAttribute("event",e);
            return "addedEvent";
        }
        else{
            return "duplicatedEvent";
        }
    }

    @GetMapping("/user/planning/delete/{idEvent}")
    public String deleteEventFromPlanning(Model model, @PathVariable long idEvent, Authentication auth) {
        Customer c= customerService.getCustomer(auth.getName());
        Event e = eventService.getEvent(idEvent);
        customerService.deleteEventFromPlanning(c,e);
        if (eventService.getEvent(idEvent)!=null) {
            model.addAttribute("event", eventService.getEvent(idEvent));
            return "deletedEventFromPlanning";
        }
        return "redirect:/planning";
    }

    @PostMapping("/user/planning/new")
    public String addEventToPlanning(Model model, String email, Event e){
        Customer c= customerService.getCustomer(email);
        if (c.addToPlanning(e)){
            model.addAttribute("event",e);
            return "addedEvent";
        }
        else{
            return "duplicatedEvent";
        }
    }

    @GetMapping("/user/planning")
    public String planning(Model model, Authentication auth, HttpServletRequest request) {
        model.addAttribute("events", customerService.getAllEventsOfACustomer(customerService.getCustomer(auth.getName())));
        return "planning";
    }

    @GetMapping("/user/planning/{category}")
    public String catalogueFilteredByCategory(Model model, @RequestBody Customer c, @PathVariable String category) {
        if (category.equalsIgnoreCase("ocio")||category.equalsIgnoreCase("restauracion")||category.equalsIgnoreCase("turismo")) {
            model.addAttribute("events", eventService.getEventsFilteredByCategory(category));
            return "planning";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //SECURITY

    /*
    @GetMapping("/private")
    public String privatePage(Model model, HttpServletRequest request) {
        String name = request.getUserPrincipal().getName();
        Customer user = customerRepository.findByName(name).orElseThrow();
        model.addAttribute("username", user.getName());
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        return "private";
    }

     */

}
