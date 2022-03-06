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
    EventHolder eventHolder;

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
        model.addAttribute("productos", eventHolder.getEvents());
        return "catalogo";
    }

    @GetMapping("/catalogo/{categoria}")
    public String filtrarCatalogoPorCategoria(Model model, @PathVariable String categoria, HttpServletRequest request) {
        if (categoria.equals("ocio") || categoria.equals("restauracion") || categoria.equals("turismo")) {
            model.addAttribute("logged", request.isUserInRole("USER"));
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("productos", eventHolder.getEventsFilteredByCategory(categoria));
            return "catalogo";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/eventos/")
    @ResponseStatus(HttpStatus.CREATED)
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
        }
    }

    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Event> borrarEvent(@PathVariable long id) {
        Event event = eventHolder.deleteEvent(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/eventos/{id}")
    public ResponseEntity<Event> actualizaEvent(@PathVariable long id, @RequestBody Event eventActualizado) {
        Event event = eventHolder.getEvent(id);
        if (event != null) {
            eventActualizado.setIdEvent(id);
            eventHolder.addEvent(eventActualizado);
            return new ResponseEntity<>(eventActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



}