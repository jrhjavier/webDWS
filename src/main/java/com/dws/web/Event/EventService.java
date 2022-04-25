package com.dws.web.Event;


import lombok.NoArgsConstructor;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

@Service
@NoArgsConstructor
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EntityManager entityManager;

    public void addEvent (Event event){

        //XSS//
        PolicyFactory policy= Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        event.setDescription(policy.sanitize(event.getDescription()));

        this.eventRepository.save(event);
    }

    public void addUpdatedEvent(long id,Event eUP ){

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

    public Long getEvent(Event e){
        return e.getId();
    }

    public Event deleteEvent(long idEvent){
        Event e=this.eventRepository.getById(idEvent);
        this.eventRepository.delete(e);
        return e;
    }

    public List<Event> getEventsFilteredByCategory(String category){  //Events filtered by category
        List<Event> eventsByCategory= eventRepository.findByCategory(category);
        return eventsByCategory;
    }

    public Event getEventByName(String name){
        TypedQuery<Event> query= entityManager.createQuery("SELECT e FROM Event e WHERE e.name = :name", Event.class);
        return query.setParameter("name", name).getSingleResult();
    }



    /*
    public Set<Event> filterEvents(float priceMin, float priceMax,String tourism, String leisure, String restaurant){

        TypedQuery<Event> queryCategoryR= entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND e.price <= :priceMax AND e.category = 'Restaurante'", Event.class);
        TypedQuery<Event> queryCategoryO= entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND e.price <= :priceMax AND e.category = 'Ocio'", Event.class);
        TypedQuery<Event> queryCategoryT= entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND e.price <= :priceMax AND e.category = 'Turismo'", Event.class);



        if(tourism == null && leisure == null && restaurant == null){
            Collection<Event> sR= queryCategoryR.setParameter("priceMin", priceMin).setParameter("priceMax",priceMax).setParameter("category",restaurant).getResultList();
            Collection<Event> sO= queryCategoryO.setParameter("priceMin", priceMin).setParameter("priceMax",priceMax).setParameter("category",leisure).getResultList();
            Collection<Event> sT= queryCategoryT.setParameter("priceMin", priceMin).setParameter("priceMax",priceMax).setParameter("category",tourism).getResultList();

            return sO;

        } else if(tourism == null && leisure == null){
            Collection<Event> l1= queryPrice.setParameter("priceMin", priceMin).setParameter("priceMax",priceMax).getResultList();
            Collection<Event> l2= queryCategoryR.setParameter("category", restaurant).getResultList();

        }else{
            return null;
        }
    }
     */
    /*
    public Collection<Event> filterEvents(float priceMin, float priceMax,List<String> categories) {

        if (categories.size() == 0) {
            TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND" +
                    " e.price <= :priceMax", Event.class);
            Collection<Event> l = query.setParameter("priceMin", priceMin).setParameter("priceMax", priceMax).getResultList();
            return l;
        } else {
            TypedQuery<Event> query2 = entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND" +
                    " e.price <= :priceMax AND e.category in categories", Event.class);
            Collection<Event> l = query2.setParameter("priceMin", priceMin).setParameter("priceMax", priceMax).setParameter("categories", categories).getResultList();
            return l;
        }
    }

     */

    public Collection<Event> filterEvents(float priceMin, float priceMax,String tourism, String leisure, String restaurant) {

        List<String> categories = new ArrayList<>();
        if (tourism != null) {
            categories.add(tourism);
        } else if (leisure != null) {
            categories.add(leisure);
        } else if (restaurant != null) {
            categories.add(restaurant);
        }

        List<Event> c = new ArrayList<>();

        if (categories.size() == 0) {
            TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND" +
                    " e.price <= :priceMax", Event.class);
            Collection<Event> l = query.setParameter("priceMin", priceMin).setParameter("priceMax", priceMax).getResultList();
            return l;
        }
        else if (categories.contains("tourism")) {
            TypedQuery<Event> query2 = entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND" +
                    " e.price <= :priceMax AND e.category = 'Turismo'", Event.class);
            List<Event> l1 = query2.setParameter("priceMin", priceMin).setParameter("priceMax", priceMax).getResultList();
            for( Event e : l1){
                c.add(e);
            }

        }
        if (categories.contains("leisure")) {
            TypedQuery<Event> query3 = entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND" +
                    " e.price <= :priceMax AND e.category = 'Ocio'", Event.class);
            List<Event> l2 = query3.setParameter("priceMin", priceMin).setParameter("priceMax", priceMax).getResultList();
            for( Event e : l2){
                c.add(e);
            }

        }
        if (categories.contains("restaurant")) {
            TypedQuery<Event> query4 = entityManager.createQuery("SELECT e FROM Event e WHERE e.price >= :priceMin AND" +
                    " e.price <= :priceMax AND e.category = 'Restaurante'", Event.class);
            List<Event> l3 = query4.setParameter("priceMin", priceMin).setParameter("priceMax", priceMax).getResultList();
            for( Event e : l3){
                c.add(e);
            }

        }
        return c;
    }


    /*
    public Event getEventByName(String name){
        Optional<Event> e=this.eventRepository.findByName(name);
        if (e.isPresent()){
            return e.get();
        }
        else{
            return null;
        }
    }

     */
}