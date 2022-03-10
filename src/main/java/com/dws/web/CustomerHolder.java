package com.dws.web;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomerHolder {

    private Map<Long, Customer> customers = new ConcurrentHashMap<>();
    private AtomicLong lastID = new AtomicLong();

    private AtomicLong lastIDEvent = new AtomicLong();

    public void addClient(Customer c) {
        long id = this.lastID.incrementAndGet();
        c.setIdClient(id);
        this.customers.put(id, c);
    }

    public Customer getCustomer(long id) {
        return this.customers.get(id);
    }

    public Collection<Customer> getAllCustomers() {
        return this.customers.values();
    }

    public Customer deleteCustomer(long id) {
        Customer c = this.customers.get(id);
        this.customers.remove(id);
        return c;
    }

    public void updateCustomer(Customer c, long id) {
        this.customers.remove(id);
        this.addClient(c);
    }

    public void addEventToPlanning(Customer c, Event e) {
        long id = lastIDEvent.incrementAndGet();
        e.setIdEvent(id);
        c.addToPlanning(e);
    }

    public void deleteEventFromPlanning(Customer c, long idEvent){
        c.deleteEvent(idEvent);
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

    public void cleanPlanning(Customer c){
        c.cleanPlanning();
    }

    public Collection<Event> getEventsOfACategory(Customer c, String category){
        Collection<Event> l = null;
        for (Event e : getAllEventsOfACustomer(c)){
            if (e.sameCategory(category)){
                l.add(e);
            }
        }
        return l;
    }


}
