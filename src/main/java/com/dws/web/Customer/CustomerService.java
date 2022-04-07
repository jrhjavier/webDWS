package com.dws.web.Customer;

import com.dws.web.Event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class CustomerService {

    //Hay que cambiar los métodos orientado al repositoriio

    @Autowired
    private CustomerRepository customerRepository;


    private AtomicLong lastID = new AtomicLong();

    private AtomicLong lastIDEvent = new AtomicLong();

    public CustomerService(){
        this.customerRepository.save(new Customer("admin@admin.com"));
    }

    public void addClient(Customer c) {
        this.customerRepository.save(c);
    }

    public void addUpdatedClient(Customer c){
        this.customerRepository.save(c);
    }

    public Customer getCustomer(String email) {
/*
        for (Map.Entry<Long, Customer> entry : this.customers.entrySet()){
            if (entry.getValue().equals(email)){
                return entry.getValue();
            }
        }

 */
        return null;

    }

    public AtomicLong getLastIDEvent() {
        return this.lastIDEvent;
    }

    public Collection<Customer> getAllCustomers() {
        return null;
        //return this.customerRepository.findAllById();
    }

    public Customer deleteCustomer(long id) {
        /*
        Customer c = this.customers.get(id);
        this.customers.remove(id);
        return c;

         */
        return null;
    }

    public void updateCustomer(Customer c, long id) {
        /*
        this.customers.remove(id);
        this.addClient(c);

         */
    }

    public void addEventToPlanning(Customer c, Event e) {
        /*
        long id = lastIDEvent.incrementAndGet();
        e.setIdEvent(id);
        c.addToPlanning(e);

         */
    }

    public Event deleteEventFromPlanning(Customer c, long idEvent){
        Event e=getAnEvent(c, idEvent);
        if (c.containsPlanning(e)){
            c.deleteEvent(idEvent);
            return e;
        }
        else{
            return null;
        }
    }

    public void updateAnEvent(Customer c, long idOldEvent, Event updatedEvent){
        c.deleteEvent(idOldEvent);
        updatedEvent.setIdEvent(idOldEvent);
        c.addToPlanning(updatedEvent);
    }

    public Collection<Event> getAllEventsOfACustomer(Customer c){
        return c.getAllEvents();
    }

    public Event getAnEvent(Customer c, long idEvent){
        return c.getAnEvent(idEvent);
    }

    public Event getAnEventByNoun(Customer c, String name){
        for(Event e : getAllEventsOfACustomer(c)){
            if(e.getName().equalsIgnoreCase(name)){
                return e;
            }
        }
        return null;
    }

    public void cleanPlanning(Customer c){
        c.cleanPlanning();
    }

    public Collection<Event> getEventsOfACategory(Customer c, String category){
        Collection<Event> l = new HashSet<>();
        for (Event e : getAllEventsOfACustomer(c)){
            if (e.sameCategory(category)){
                l.add(e);
            }
        }
        return l;
    }
}
