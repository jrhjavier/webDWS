package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RequestMapping("/api")
@RestController
public class CustomerRESTController {

    @Autowired
    CustomerHolder customerHolder;

    @Autowired
    EventHolder eventHolder;

    //CUSTOMER

    @PostMapping("/customer/new")
    public ResponseEntity<Customer> newCustomerAPI(@RequestBody Customer c) {
        customerHolder.addClient(c);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @DeleteMapping("/customer/delete/{email}")
    public ResponseEntity<Customer> deleteCustomerAPI(@PathVariable String email) {
        Customer c= customerHolder.getCustomer(email);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customer/update/{email}")
    public ResponseEntity<Customer> updateCustomerAPI(@PathVariable String email, @RequestBody Customer updatedCustomer) {
        Customer c=customerHolder.getCustomer(email);
        if (c != null) {
            long idCustomer=c.getIdClient();
            customerHolder.deleteCustomer(idCustomer);
            updatedCustomer.setIdClient(idCustomer);
            customerHolder.addUpdatedClient(updatedCustomer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<Collection> getAllCustomersAPI() {
        Collection<Customer> customers = customerHolder.getAllCustomers();
        if (customers != null) {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<Customer> getCustomerAPI(@PathVariable String email){
        Customer c=customerHolder.getCustomer(email);
        if (c!=null){
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //PLANNING (Revisar)

    @PostMapping("/events/{idEvent}/new/{email}")
    public ResponseEntity<Event> newEventAPI(@PathVariable long idEvent, @PathVariable String email) {
        Customer c= customerHolder.getCustomer(email);
        Event e=eventHolder.getEvent(idEvent);
        c.addToPlanning(e);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @DeleteMapping("/planning/{email}/delete/{idEvent}")
    public ResponseEntity<Event> deleteEventAPI(@PathVariable String email, @PathVariable long idEvent) {
        Customer c= customerHolder.getCustomer(email);
        Event event = customerHolder.deleteEventFromPlanning(c, idEvent);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/planning/{email}/{idEvent}")
    public ResponseEntity<Event> updateEventAPI(@PathVariable String email, @PathVariable long idEvent, @RequestBody Event updatedEvent) {
        Customer c= customerHolder.getCustomer(email);
        Event event = c.getAnEvent(idEvent);
        if (event != null) {
            updatedEvent.setIdEvent(idEvent);
            c.addToPlanning(event);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/planning/{email}")
    public ResponseEntity<Collection> getAllEventOfACustomerAPI(@PathVariable String email) {
        Customer c=customerHolder.getCustomer(email);
        Collection<Event> events = customerHolder.getAllEventsOfACustomer(c);
        if (!events.isEmpty()) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events/{email}/{idEvent}")  //Products by id
    public ResponseEntity<Event> getEventAPI(@PathVariable String email, @PathVariable long idEvent){
        Customer c= customerHolder.getCustomer(email);
        Event e= customerHolder.getAnEvent(c, idEvent);

        if (e!=null){
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/planning/{email}/category/{category}")
    public ResponseEntity<Collection> planningFilteredByCategoryAPI(@PathVariable String email, @PathVariable String category) {
        if (category.equalsIgnoreCase("ocio")||category.equalsIgnoreCase("restauracion")||category.equalsIgnoreCase("turismo")) {
            Customer c=customerHolder.getCustomer(email);
            return new ResponseEntity<>(customerHolder.getEventsOfACategory(c, category), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
