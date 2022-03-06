package com.dws.web;

import java.util.Objects;

public class Evento {
    private long idEvento;
    private String nombre;
    private String descripcion;
    private String precio;
    private String categoria;
    private int ValoracionMedia = 0;


    public Evento() {
    }

    public Evento(long idSitio, String nombre, String descripcion, String precio, String categoria) {
        this.idEvento = idSitio;
        this.nombre=nombre;
        this.precio=precio;
        this.descripcion=descripcion;
        this.categoria = categoria;
    }

    public long getId() {
        return this.idEvento;
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
        return this.idEvento;
    }

    public void setIdEvento(long idSitio) {
        this.idEvento = idSitio;
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
        return "Sitio:" + "\n" + "IdSitio: " + this.idEvento + "\n" + "Nombre: " +
                this.nombre + "\n" + "Descripcion: " + this.descripcion + "\n" +
                "Precio: " + this.precio + "\n" +
                "Valoracion Media: " + this.ValoracionMedia;
    }

    public boolean mismaCategoria(String categoria){
        return this.categoria==categoria;
    }

    @Override
    public boolean equals(Object o) {  //Comparamos los sitios por su id
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        System.out.println(idEvento + " " + evento.idEvento);
        return idEvento == evento.idEvento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento);
    }
}
