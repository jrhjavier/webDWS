package com.dws.web;

import java.util.Objects;

public class Place {
    private long idSitio;
    private String nombre;
    private String descripcion;
    private String precio;
    private String categoria;
    private int ValoracionMedia = 0;


    public Place() {
    }

    public Place(long idSitio, String nombre, String descripcion, String precio, String categoria) {
        this.idSitio = idSitio;
        this.nombre=nombre;
        this.precio=precio;
        this.descripcion=descripcion;
        this.categoria = categoria;
    }

    public long getIdSitio() {
        return this.idSitio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getPrecio() {
        return this.precio;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public int getStarsAverage() {
        return this.ValoracionMedia;
    }

    public long incrementAndGetId(){  //el increment no sabemos por que es, habra que incrementar algo
        return this.idSitio;
    }

    public void setIdSitio(long idSitio) {
        this.idSitio = idSitio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStarsAverage(int ValoracionMedia) {
        this.ValoracionMedia = ValoracionMedia;
    }


    @Override
    public String toString() {
        return "Sitio:" + "\n" + "IdSitio: " + this.idSitio + "\n" + "Nombre: " +
                this.nombre + "\n" + "Descripcion: " + this.descripcion + "\n" +
                "Precio: " + this.precio + "\n" +
                "Valoracion Media: " + this.ValoracionMedia;
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
