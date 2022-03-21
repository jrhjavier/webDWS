package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api")
@RestController
public class CustomerRESTController {

    @Autowired
    CustomerHolder customerHolder;

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

    @PostMapping("/events/new/{email}")
    public ResponseEntity<Event> newEventAPI(@PathVariable String email, @RequestBody Event event) {
        Customer c= customerHolder.getCustomer(email);
        customerHolder.addEventToPlanning(c, event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @DeleteMapping("/planning/{email}/{id}")
    public ResponseEntity<Event> deleteEventAPI(@PathVariable String email, @PathVariable long id) {
        Customer c= customerHolder.getCustomer(email);
        Event event = customerHolder.deleteEventFromPlanning(c, id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/planning/{email}/{id}")
    public ResponseEntity<Event> updateEventAPI(@PathVariable String email, @PathVariable long id, @RequestBody Event updatedEvent) {
        Customer c= customerHolder.getCustomer(email);
        Event event = customerHolder.getAnEvent(c, id);
        if (event != null) {
            updatedEvent.setIdEvent(id);
            customerHolder.addEventToPlanning(c, updatedEvent);
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

    @GetMapping("/events/{email}/{id}")  //Products by id
    public ResponseEntity<Event> getEventAPI(@PathVariable String email, @PathVariable long id){
        Customer c= customerHolder.getCustomer(email);
        Event e= customerHolder.getAnEvent(c, id);

        if (e!=null){
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
