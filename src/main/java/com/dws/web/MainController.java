package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
<<<<<<< HEAD
    EventoHolder eventoHolder;
=======
    EventHolder eventHolder;
>>>>>>> master

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("logged", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model, HttpServletRequest request) {
        model.addAttribute("logged", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
<<<<<<< HEAD
        model.addAttribute("productos", eventoHolder.getEventos());
=======
        model.addAttribute("productos", eventHolder.getEvents());
>>>>>>> master
        return "catalogo";
    }

    @GetMapping("/catalogo/{categoria}")
    public String filtrarCatalogoPorCategoria(Model model, @PathVariable String categoria, HttpServletRequest request) {
        if (categoria.equals("ocio") || categoria.equals("restauracion") || categoria.equals("turismo")) {
            model.addAttribute("logged", request.isUserInRole("USER"));
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
<<<<<<< HEAD
            model.addAttribute("productos", eventoHolder.getEventosFiltradosPorCategoria(categoria));
=======
            model.addAttribute("productos", eventHolder.getEventsFilteredByCategory(categoria));
>>>>>>> master
            return "catalogo";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/eventos/")
    @ResponseStatus(HttpStatus.CREATED)
<<<<<<< HEAD
    public Evento nuevoEvento(@RequestBody Evento evento) {
        long id = eventoHolder.getLastID().incrementAndGet();
        evento.setIdEvento(id);
        eventoHolder.addEvento(evento);
        return evento;
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<Evento> getEvento(@PathVariable long id) {
        Evento evento = eventoHolder.getEvento(id);
        if (evento != null) {
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(evento, HttpStatus.NOT_FOUND);
=======
    public Event nuevoEvent(@RequestBody Event event) {
        long id = eventHolder.getLastID().incrementAndGet();
        event.setIdEvent(id);
        eventHolder.addEvent(event);
        return event;
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable long id) {
        Event event = eventHolder.getEvent(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(event, HttpStatus.NOT_FOUND);
>>>>>>> master
        }
    }

    @DeleteMapping("/eventos/{id}")
<<<<<<< HEAD
    public ResponseEntity<Evento> borrarEvento(@PathVariable long id) {
        Evento evento = eventoHolder.deleteEvento(id);
        if (evento != null) {
            return new ResponseEntity<>(evento, HttpStatus.OK);
=======
    public ResponseEntity<Event> borrarEvent(@PathVariable long id) {
        Event event = eventHolder.deleteEvent(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
>>>>>>> master
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/eventos/{id}")
<<<<<<< HEAD
    public ResponseEntity<Evento> actualizaEvento(@PathVariable long id, @RequestBody Evento eventoActualizado) {
        Evento evento = eventoHolder.getEvento(id);
        if (evento != null) {
            eventoActualizado.setIdEvento(id);
            eventoHolder.addEvento(eventoActualizado);
            return new ResponseEntity<>(eventoActualizado, HttpStatus.OK);
=======
    public ResponseEntity<Event> actualizaEvent(@PathVariable long id, @RequestBody Event eventActualizado) {
        Event event = eventHolder.getEvent(id);
        if (event != null) {
            eventActualizado.setIdEvent(id);
            eventHolder.addEvent(eventActualizado);
            return new ResponseEntity<>(eventActualizado, HttpStatus.OK);
>>>>>>> master
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



<<<<<<< HEAD
}
=======
}
>>>>>>> master
