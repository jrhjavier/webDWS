package com.dws.web.Event;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@NoArgsConstructor
public class EventService {

    private AtomicLong lastIDEvent = new AtomicLong();

    @Autowired
    private EventRepository eventRepository;

    //HECHO
    public void addEvent (Event event){
        long id = this.lastIDEvent.incrementAndGet();
        event.setIdEvent(id);
        this.eventRepository.save(event);
    }

    //HECHO
    public void addUpdatedEvent(long id,Event eUP ){
        Event eFind = eventRepository.getById(id);
        this.eventRepository.delete(eFind);
        eUP.setIdEvent(eFind.getIdEvent());
        this.eventRepository.save(eUP);
        //Add metodo a repositorio
        //Nose si es así
    }

    //HECHO
    public Collection<Event> getEvents(){
        Collection<Event> allEvents=new HashSet<>();
        List<Event> l= eventRepository.findAll();
        for(Event e : l){
            allEvents.add(e);
        }
        return allEvents;
    }

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

    //HECHO
    public Long getEvent(Event e){
        return e.getId();
    }

    //HECHO
    public Event deleteEvent(long id){
        Event e = this.eventRepository.getById(id);
        this.eventRepository.delete(e);
        return e;
    }

    public AtomicLong getLastIDEvent() {
        return this.lastIDEvent;
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