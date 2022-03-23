package com.dws.web;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class Event {

    interface Basico {}

    @JsonView(Customer.Basico.class)
    private long idEvent;

    @JsonView(Customer.Basico.class)
    private String name;

    @JsonView(Customer.Basico.class)
    private String description;

    @JsonView(Customer.Basico.class)
    private int price;

    @JsonView(Customer.Basico.class)
    private String category;

    Map<Long, Review> reviews=new ConcurrentHashMap<>();

    private AtomicLong lastIDReview = new AtomicLong();

    public Event() {
    }

    public Event(String name, String category, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }


    public long getId() {
        return this.idEvent;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }


    public long incrementAndGetId() {  //el increment no sabemos por que es, habra que incrementar algo
        return this.idEvent++;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public long getIdEvent() {
        return idEvent;
    }

    //REVIEW

    public void addReviewToThisEvent(Review r){
        long idReview=this.lastIDReview.incrementAndGet();
        r.setIdReview(idReview);
        this.reviews.put(r.getIdReview(), r);
    }

    public void addUpdatedReviewToThisEvent(Review r){
        this.reviews.put(r.getIdReview(), r);
    }

    public Review deleteReviewOfThisEvent(long idReview){
        Review r=this.reviews.get(idReview);
        this.reviews.remove(idReview);
        return r;
    }

    public Review getReview(long idReview){
        return this.reviews.get(idReview);
    }

    public Collection<Review> getAllReviews(){
        return this.reviews.values();
    }

    @Override
    public String toString() {
        return "Evento:" + "\n" + "IdEvento: " + this.idEvent + "\n" + "nombre: " +
                this.name + "\n" + "descripción: " + this.description + "\n" +
                "precio: " + this.price + "\n";
    }

    public boolean sameCategory(String category) {
        return this.category.equalsIgnoreCase(category);
    }

    @Override
    public boolean equals(Object o) {  //We compare the events by their id
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        System.out.println(idEvent + " " + event.idEvent);
        return this.idEvent == event.idEvent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvent);
    }

}
