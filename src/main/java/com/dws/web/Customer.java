package com.dws.web;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Customer {

    interface Basico {}

    @JsonView(Basico.class)
    private long idCustomer;

    @JsonView(Basico.class)
    private String name;

    @JsonView(Basico.class)
    private String surname;

    @JsonView(Basico.class)
    private String email;

    @JsonView(Basico.class)
    private String phoneNumber;

    @JsonView(Basico.class)
    private String passwd;

    @JsonView(Basico.class)
    private String address;

    private Map<Long, Event> planning = new ConcurrentHashMap<>();

    public Customer(){
    }

    public Customer(String name, String surname, String email, String phoneNumber, String passwd, String address){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwd = passwd;
        this.address = address;
    }

    public Customer (String email){
        this.email=email;
    }

    public void setIdClient(long idClient) {
        this.idCustomer = idClient;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public long getIdClient() {
        return this.idCustomer;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return "Datos del cliente :" + "\n" +
                "idCliente = " + this.idCustomer + "\n" +
                "Nombre : " + this.name + "\n" +
                "Apellido : " + this.surname + "\n" +
                "Email : " + this.email + "\n" +
                "Telefono : " + this.phoneNumber + "\n" +
                "Password : " + this.passwd + "\n" +
                "Direccion : " + this.address + "\n";
    }

    //PLANNING

    public boolean addToPlanning(Event e){
        if (!this.planning.containsKey(e.getId())){
            this.planning.put(e.getId(), e);
            return true;
        }
        else{
            return false;
        }
    }

    public Collection<Event> getPlanning(){
        return this.planning.values();
    }

    public void deleteEvent(long idEvent){
        this.planning.remove(idEvent);
    }

    public void cleanEvent(){
        this.planning.clear();
    }


    public boolean containsPlanning(Event e1){
        Event e = inPlanning(e1);
        return e != null;
    }

    private Event inPlanning(Event e1){
        for(Event e : this.planning.values()){
            if(e.getId() == e1.getId()) return e;
        }
        return null;
    }

    public Collection<Event> getAllEvents(){
        Collection<Event> c = this.planning.values();
        return c;
    }

    public Event getAnEvent(long idEvent){
        return this.planning.get(idEvent);
    }

    public void cleanPlanning(){
        this.planning.clear();
    }

    public boolean equals(String email) {
        return this.email.equalsIgnoreCase(email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
