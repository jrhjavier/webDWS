package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/api")
@RestController
public class ReviewRESTController {

    @Autowired
    ReviewHolder reviewHolder;

    @Autowired
    CustomerHolder customerHolder;

    @Autowired
    EventHolder eventHolder;

    @GetMapping("/reviews/{id}") //Reviews de un cliente
    public ResponseEntity<Collection> getReviewsOfAClientAPI(@PathVariable long id){
        Customer c= customerHolder.getCustomer(id);
        Collection<Review> reviews = reviewHolder.getReviewsOfAClient(c);
        if (reviews!=null){
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/reviews/{id}") //Reviews de un Evento
    public ResponseEntity<Collection> getReviewsOfAnEventAPI(@PathVariable long id){
        Event e= eventHolder.getEvent(id);
        Collection<Review> reviews = reviewHolder.getReviewsOfAnEvent(e);
        if (reviews!=null){
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/reviews") //Todas las reviews
    public ResponseEntity<Collection> getAllReviewsAPI(){
        Collection<Review> reviews = reviewHolder.getAllReviews();
        if (reviews!=null){
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
