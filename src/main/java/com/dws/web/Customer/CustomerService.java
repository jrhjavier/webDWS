package com.dws.web.Customer;

import com.dws.web.Event.Event;
import com.dws.web.Event.EventRepository;
import com.dws.web.Review.Review;
import com.dws.web.Review.ReviewRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private ReviewRepository reviewRepository;

    public void addClient(Customer c) {
        List<String> roles=new ArrayList<>();
        roles.add("USER");
        c.setRoles(roles);
        //BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
        //c.setPassword(encoder.encode(c.getPasswd()));
        this.customerRepository.save(c);
    }

    public void addUpdatedClient(Customer c) {
        List<String> roles=new ArrayList<>();
        roles.add("USER");
        c.setRoles(roles);
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

    public boolean addEventToPlanning(long idCustomer, Event e) {
        try {
            Customer c = customerRepository.getById(idCustomer);
            c.addToPlanning(e);
            customerRepository.saveAndFlush(c);
            return true;
        }catch (Exception ex){
            return false;
        }
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
        return eventRepository.findByCustomers(c);
    }

    public Collection<Review> getAllReviewsOfACustomer(Customer c){
        return reviewRepository.findByCustomer(c);
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

    public boolean containsCustomer(Customer customer){
        for (Customer c:this.customerRepository.findAll()){
            if (customer.getEmail().equals(c.getEmail())){
                return true;
            }
        }
        return false;
    }

    public boolean esAdmin(Customer c){
        if (c.getRoles().contains("ADMIN")){
            return true;
        }
        else{
            return false;
        }
    }
}