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

    //Hay que cambiar los métodos orientado al repositoriio

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EventRepository eventRepository;

    private AtomicLong lastID = new AtomicLong();

    private AtomicLong lastIDEvent = new AtomicLong();



    public void addClient(Customer c) {
        this.customerRepository.save(c);
    }

    //HECHO
    public void addUpdatedClient(Customer c) {
        this.customerRepository.save(c);
    }

    //HECHO
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


    public AtomicLong getLastIDEvent() {
        return this.lastIDEvent;
    }

    //HECHO
    public Collection<Customer> getAllCustomers() {
        Collection<Customer> allCustomers = new HashSet<>();
        //allCustomers = customerRepository.findAll();

        List<Customer> l = customerRepository.findAll();
        for (Customer c : l) {
            allCustomers.add(c);
        }
        return allCustomers;
    }

    //HECHO
    public Customer deleteCustomer(long id) {
        Customer c = customerRepository.getById(id);
        customerRepository.delete(c);
        return c;
    }

    //HECHO
    public void updateCustomer(Customer c, long id) {
        Customer c1 = customerRepository.getById(id);
        customerRepository.delete(c1);
        customerRepository.saveAndFlush(c);
    }

    public void addEventToPlanning(long idCustomer, Event e) {
        long id = lastIDEvent.incrementAndGet();
        e.setIdEvent(id);
        Customer c = customerRepository.getById(idCustomer);
        c.addToPlanning(e);
    }


    public Event deleteEventFromPlanning(Customer c, long idEvent) {
        Optional<Customer> customer=customerRepository.findByEmail(c.getEmail());
        if (customer.isPresent()){
            Customer c1=customer.get();
            Event e=eventRepository.getById(idEvent);
            e=c1.inPlanning(e);
            if (e!=null){
                c1.deleteEvent(e.getIdEvent());
                return e;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    public void updateAnEvent(Customer c, long idOldEvent, Event updatedEvent) {
        c.deleteEvent(idOldEvent);
        updatedEvent.setIdEvent(idOldEvent);
        c.addToPlanning(updatedEvent);
    }

    public Collection<Event> getAllEventsOfACustomer(Customer c) {
        Collection<Event> allEventsOfACustomer = new HashSet<>();

        Optional<Customer> c1 = customerRepository.findByEmail(c.getEmail());
        if (c1.isPresent()) {
            Customer c2 = c1.get();
            Collection<Event> l = c2.getAllEvents();
            for (Event e1 : l) {
                for (Event e2 : this.eventRepository.findAll()) {
                    if (e1.equals(e2)) {
                        allEventsOfACustomer.add(e2);
                    }
                }
            }
        }

        return allEventsOfACustomer;
    }

    public Event getAnEvent(Customer c, long idEvent) {
        Event e=this.eventRepository.getById(idEvent);
        Optional<Customer> c1=this.customerRepository.findByEmail(c.getEmail());
        if (c1.isPresent()){
            e=c1.get().inPlanning(e);
            if (e!=null){
                return e;
            }
        }
        return null;
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

}

    /*
    public Collection<Event> getEventsOfACategory(Customer c, String category){
        Collection<Event> allEventsOfACustomerByCategory=new HashSet<>();

        Optional<Customer> c1=customerRepository.findByEmail(c.getEmail());
        if (c1.isPresent()) {
            Customer c2 = c1.get();
            Collection<Event> l = c2.getAllEvents();
            for (Event e1 : l) {
                for (Event e2 : this.eventRepository.findAll()){
                    if (e1.equals(e2)){
                        allEventsOfACustomerByCategory.add(e2);
                    }
                }
            }
        }

        return allEventsOfACustomerByCategory;
}
     */