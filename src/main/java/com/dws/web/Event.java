package com.dws.web;

import java.util.Objects;

public class Event {
    private long idEvent;
    private String name;
    private String description;
    private String price;
    private String category;
    private int averageRating = 0;  //valoracion media


    public Event() {
    }

    public Event(long idPlace, String name, String description, String price, String category) {
        this.idEvent = idPlace;
        this.name=name;
        this.price=price;
        this.description=description;
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

    public String getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public int getStarsAverage() {
        return this.averageRating;
    }

    public long incrementAndGetId(){  //el increment no sabemos por que es, habra que incrementar algo
        return this.idEvent;
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

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStarsAverage(int averageRating) {
        this.averageRating = averageRating;
    }


    @Override
    public String toString() {
        return "Sitio:" + "\n" + "IdSitio: " + this.idEvent + "\n" + "name: " +
                this.name + "\n" + "description: " + this.description + "\n" +
                "price: " + this.price + "\n" +
                "Valoracion Media: " + this.averageRating;
    }

    public boolean sameCategory(String category){
        return Objects.equals(this.category, category);
    }

    @Override
    public boolean equals(Object o) {  //Comparamos los sitios por su id
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