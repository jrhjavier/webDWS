package com.dws.web;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventoHolder {

    private Map<Long, Evento> eventos = new ConcurrentHashMap<>();
    private AtomicLong lastID = new AtomicLong();  //por ahora esto no sirve para nada, lo tenia el profe

    public void addEvento (Evento evento){
        long id=lastID.incrementAndGet();
        evento.setIdEvento(id);
        eventos.put(id, evento);
    }

    public Collection<Evento> getEventos(){
        return eventos.values();
    }

    public Evento getEvento(long id){
        return eventos.get(id);
    }

    public Evento deleteEvento(long id){
        Evento evento = getEvento(id);
        eventos.remove(evento);
        return evento;
    }

    public AtomicLong getLastID() {
        return this.lastID;
    }

    public List<Evento> getEventosFiltradosPorCategoria(String categoria){
        List<Evento> l=null;
        for (Evento e : this.eventos.values()){
            if (e.mismaCategoria(categoria)){
                l.add(e);
            }
        }
        return l;
    }
}
