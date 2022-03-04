package com.dws.web;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SitioHolder {  //gestor de sitios -> acciones con los sitios
    private Map<Long, Sitio> sitios = new ConcurrentHashMap<>();
    private AtomicLong lastID = new AtomicLong();  //por ahora esto no sirve para nada, lo tenia el profe

    public void addSitio (Sitio sitio){
        long id = sitio.incrementAndGetId();  //incrementar por que?
        sitio.setIdSitio(id);
        sitios.put(id, sitio);
    }

    public Collection<Sitio> getSitios(){
        return sitios.values();
    }

    public Sitio getSitio(long id){
        return sitios.get(id);
    }

}
