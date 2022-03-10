/*
package com.dws.web;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Review {

    private long idReview;
    private int stars;
    private String message;

    private Customer client=new Customer();

    private Event event=new Event();

    private AtomicLong lastID = new AtomicLong();

    public Review(){
    }

    public Review (long id, int stars, String message, Customer c, Event e){
        this.idReview=id;
        this.stars=stars;
        this.message=message;
        this.client=c;
        this.event=e;
    }

    public long getIdReview() {
        return idReview;
    }

    public int getStars() {
        return stars;
    }

    public String getMessage() {
        return message;
    }

    public Customer getClient() {
        return client;
    }

    public Event getEvent() {
        return event;
    }


    public void setIdReview(long idReview) {
        this.idReview = idReview;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public AtomicLong getLastID() {
        return lastID;
    }

    public void setLastID(AtomicLong lastID) {
        this.lastID = lastID;
    }

    public boolean reviewOfAnEvent(Event e){
        return this.event.equals(e);
    }

    public boolean reviewOfAClient(Customer c){
        return this.event.equals(c);
    }



    @Override
    public String toString() {
        return "Review: " + "\n" +
                "IDReview: " + this.idReview + "\n" +
                "Estrellas: " + this.stars + "\n" +
                "Mensaje: " + this.message + "\n" +
                "Cliente=" + this.client + "\n" +
                "Evento: " + this.event + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return idReview == review.idReview;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReview);
    }
}


 */