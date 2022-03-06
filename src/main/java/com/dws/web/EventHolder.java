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
        long id=lastID.incrementAndGet();
        event.setIdEvent(id);
        events.put(id, event);
    }

    public Collection<Event> getEvents(){
        return events.values();
    }

    public Event getEvent(long id){
        return events.get(id);
    }

    public Event deleteEvent(long id){
        Event event = getEvent(id);
        events.remove(event);
        return event;
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