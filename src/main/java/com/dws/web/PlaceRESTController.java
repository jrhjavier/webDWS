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
public class PlaceRESTController {  //union entre java y web
    @Autowired
    PlaceHolder placeHolder;

    @PostMapping("/sitio")
    public ResponseEntity<Place> create(@RequestBody Place place){
        this.placeHolder.addSitio(place);
        return new ResponseEntity<>(place, HttpStatus.CREATED);
    }


}
