package com.dws.web.Event;


import lombok.NoArgsConstructor;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@NoArgsConstructor
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EntityManager entityManager;

    //HECHO
    public void addEvent (Event event){

        //XSS//
        PolicyFactory policy= Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        event.setDescription(policy.sanitize(event.getDescription()));

        this.eventRepository.save(event);
    }

    //HECHO
    public void addUpdatedEvent(long id,Event eUP ){
        /*Event eFind = eventRepository.getById(id);
        this.eventRepository.delete(eFind);
        eUP.setIdEvent(eFind.getIdEvent());


        this.eventRepository.save(eUP);
        //Add metodo a repositorio
        //Nose si es así

         */

        //XSS//
        PolicyFactory policy= Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        eUP.setDescription(policy.sanitize(eUP.getDescription()));

        this.eventRepository.saveAndFlush(eUP);
    }


    public Collection<Event> getEvents(){
        Collection<Event> allEvents=new HashSet<>();
        List<Event> l= eventRepository.findAll();
        for(Event e : l){
            allEvents.add(e);
        }
        return allEvents;
    }

    public Event getEvent(long idEvent){
        TypedQuery<Event> query= entityManager.createQuery("SELECT e FROM Event e WHERE e.idEvent = :idEvent", Event.class);
        return query.setParameter("idEvent", idEvent).getSingleResult();
    }

    /*
    //HECHO
    public Event getEvent(long id){
        Optional<Event> e=this.eventRepository.findById(id);
        if (e.isPresent()){
            return e.get();
        }
        else{
            return null;
        }
    }
     */

    //HECHO
    public Long getEvent(Event e){
        return e.getId();
    }

    //HECHO
    public Event deleteEvent(long idEvent){
        Event e=this.eventRepository.getById(idEvent);
        this.eventRepository.delete(e);
        return e;
    }


    //HECHO
    public List<Event> getEventsFilteredByCategory(String category){  //Events filtered by category
        List<Event> eventsByCategory= eventRepository.findByCategory(category);
        return eventsByCategory;
    }

    //HECHO
    public Event getEventByName(String name){
        Optional<Event> e=this.eventRepository.findByName(name);
        if (e.isPresent()){
            return e.get();
        }
        else{
            return null;
        }

    }
}
































/*
*
* @Autowired
    EventRepository eventRepository;

    public Optional<Event> existEventById(long id){
        return eventRepository.findById(id);
    }
* */

























/*
*
* @Autowired
    EventRepository eventRepository;

    public Optional<Event> existEventById(long id){
        return eventRepository.findById(id);
    }
* */