package com.dws.web;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventHolder {

    private Map<Long, Event> events = new ConcurrentHashMap<>();
    private AtomicLong lastID = new AtomicLong();  //por ahora esto no sirve para nada, lo tenia el profe

    public void addEvent (Event event){
        long id = this.lastID.incrementAndGet();
        event.setIdEvent(id);
        this.events.put(id, event);
    }

    public Collection<Event> getEvents(){
        return this.events.values();
    }

    public Event getEvent(long id){
        return this.events.get(id);
    }

    public Event deleteEvent(long id){
        Event event = this.events.get(id);
        this.events.remove(id);
        return event;  //el return para que?
    }

    public AtomicLong getLastID() {
        return this.lastID;
    }

    public List<Event> getEventsFilteredByCategory(String category){  //eventos filtrados por categoria
        List<Event> l=null;
        for (Event e : this.events.values()){
            if (e.sameCategory(category)){
                l.add(e);
            }
        }
        return l;
    }
}