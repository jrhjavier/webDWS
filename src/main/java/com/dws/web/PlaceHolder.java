package com.dws.web;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PlaceHolder {  //gestor de sitios -> acciones con los sitios
    private Map<Long, Place> sitios = new ConcurrentHashMap<>();
    private AtomicLong lastID = new AtomicLong();  //por ahora esto no sirve para nada, lo tenia el profe

    public void addSitio (Place place){
        long id = place.incrementAndGetId();  //incrementar por que?
        place.setIdSitio(id);
        sitios.put(id, place);
    }

    public Collection<Place> getSitios(){
        return sitios.values();
    }

    public Place getSitio(long id){
        return sitios.get(id);
    }

}
