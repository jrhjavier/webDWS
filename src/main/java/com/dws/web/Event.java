package com.dws.web;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.*;

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

    public void setIdEvent(long idPlace) {
        this.idEvent = idPlace;
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


    @Override
    public String toString() {
        return "Sitio:" + "\n" + "IdSitio: " + this.idEvent + "\n" + "name: " +
                this.name + "\n" + "description: " + this.description + "\n" +
                "price: " + this.price + "\n";
    }

    public boolean sameCategory(String category) {
        return Objects.equals(this.category, category);
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
