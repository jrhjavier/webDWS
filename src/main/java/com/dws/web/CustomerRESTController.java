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
public class CustomerRESTController {

    @Autowired
    EventHolder customerHolder;

    @PostMapping("/events/")
    public ResponseEntity<Event> newEvent(@RequestBody Event event) {
        long id = eventHolder.getLastIDEvent().incrementAndGet();
        event.setIdEvent(id);
        eventHolder.addEvent(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }
}
