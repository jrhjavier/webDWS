package com.dws.web;

import java.util.Objects;

public class Place {
    private long idSitio;
    private String name;
    private String description;
    private String price;
    private String category;
    private String location;  //ubicacion
    private int starsAverage = 0;


    public Place() {
    }

    public Place(long idSitio, String name, String description, String price, String category, String location) {
        this.idSitio = idSitio;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.location = location;
    }

    
    public long getIdSitio() {
        return this.idSitio;
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
        return this.starsAverage;
    }

    public String getLocation() {
        return location;
    }

    public long incrementAndGetId(){  //el increment no sabemos por que es, habra que incrementar algo
        return this.idSitio;
    }


    public void setIdSitio(long idSitio) {
        this.idSitio = idSitio;
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

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStarsAverage(int ValoracionMedia) {
        this.starsAverage = ValoracionMedia;
    }


    @Override
    public String toString() {
        return "Sitio:" + "\n" + "IdSitio: " + this.idSitio + "\n" + "Nombre: " +
                this.name + "\n" + "Descripcion: " + this.description + "\n" +
                "Precio: " + this.price + "\n" +
                "Valoracion Media: " + this.starsAverage;
    }

    @Override
    public boolean equals(Object o) {  //Comparamos los sitios por su id
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        System.out.println(idSitio + " " + place.idSitio);
        return idSitio == place.idSitio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSitio);
    }
}
