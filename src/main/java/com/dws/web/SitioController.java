package com.dws.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SitioController {
    private Map<Long, Sitio> sitios = new ConcurrentHashMap<>();
    private AtomicLong lastID = new AtomicLong();

    public void addSitio (Sitio sitio){
        long id = sitio.incrementAndGetId();  //incrementar por que?
        sitio.setIdSitio(id);
        sitios.put(id, sitio);
    }


}
