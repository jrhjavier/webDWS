package com.dws.web.Customer;

import com.dws.web.Event.Event;
import com.dws.web.Event.EventRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Service
@NoArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EventRepository eventRepository;

    public void addClient(Customer c) {
        this.customerRepository.save(c);
    }

    public void addUpdatedClient(Customer c) {
        this.customerRepository.save(c);
    }

    public Customer getClient(long id) {
        Optional<Customer> c = customerRepository.findById(id);
        if (c.isPresent()) {
            return c.get();
        } else {
            return null;
        }
    }


    public Customer getCustomer(String email) {
        Optional<Customer> c = this.customerRepository.findByEmail(email);
        if (c.isPresent()) {
            return c.get();
        } else {
            return null;
        }

    }

    public Collection<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer deleteCustomer(long id) {
        Customer c = customerRepository.getById(id);
        customerRepository.delete(c);
        return c;
    }

    public void updateCustomer(Customer c, long id) {
        Customer c1 = customerRepository.getById(id);
        customerRepository.delete(c1);
        customerRepository.save(c);
    }

    public void addEventToPlanning(long idCustomer, Event e) {
        Customer c = customerRepository.getById(idCustomer);
        c.addToPlanning(e);
    }


    public Event deleteEventFromPlanning(Customer c, Event e) {
        c.deleteEvent(e);
        e.unassignCustomer(c);
        customerRepository.saveAndFlush(c);
        eventRepository.saveAndFlush(e);
        return e;
    }

    /*
    public void updateAnEvent(Customer c, long idOldEvent, Event updatedEvent) {
        c.deleteEvent(idOldEvent);
        updatedEvent.setIdEvent(idOldEvent);
        c.addToPlanning(updatedEvent);
    }
     */

    public Collection<Event> getAllEventsOfACustomer(Customer c) {
        return c.getAllEvents();
    }

    public Event getAnEvent(Customer c, long idEvent) {
        return c.getAnEvent(idEvent);
    }

    public Event getAnEventByNoun(Customer c, String name) {
        for (Event e : getAllEventsOfACustomer(c)) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    public void cleanPlanning(Customer c) {
        c.cleanPlanning();
    }

    public Customer getCustomerByEmail(String email){
        Optional<Customer> c=customerRepository.findByEmail(email);
        if (c.isPresent()){
            return c.get();
        }
        else{
            return null;
        }
    }

    public Customer getCustomerByName(String name){
        Optional<Customer> c=customerRepository.findByName(name);
        if (c.isPresent()){
            return c.get();
        }
        else{
            return null;
        }
    }
}