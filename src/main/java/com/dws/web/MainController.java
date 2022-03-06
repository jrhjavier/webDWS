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
    EventoHolder eventoHolder;

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
        model.addAttribute("productos", eventoHolder.getEventos());
        return "catalogo";
    }

    @GetMapping("/catalogo/{categoria}")
    public String filtrarCatalogoPorCategoria(Model model, @PathVariable String categoria, HttpServletRequest request) {
        if (categoria.equals("ocio") || categoria.equals("restauracion") || categoria.equals("turismo")) {
            model.addAttribute("logged", request.isUserInRole("USER"));
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("productos", eventoHolder.getEventosFiltradosPorCategoria(categoria));
            return "catalogo";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/eventos/")
    @ResponseStatus(HttpStatus.CREATED)
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
        }
    }

    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Evento> borrarEvento(@PathVariable long id) {
        Evento evento = eventoHolder.deleteEvento(id);
        if (evento != null) {
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/eventos/{id}")
    public ResponseEntity<Evento> actualizaEvento(@PathVariable long id, @RequestBody Evento eventoActualizado) {
        Evento evento = eventoHolder.getEvento(id);
        if (evento != null) {
            eventoActualizado.setIdEvento(id);
            eventoHolder.addEvento(eventoActualizado);
            return new ResponseEntity<>(eventoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



}
