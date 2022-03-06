package com.dws.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class RESTController {
    @Autowired
    EventoHolder eventoHolder;

    @PostMapping("/sitio")
    public ResponseEntity<Evento> create(@RequestBody Evento evento){
        this.eventoHolder.addEvento(evento);
        return new ResponseEntity<>(evento, HttpStatus.CREATED);
    }


}
