package com.dws.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Customer {
    private long idClient;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String passwd;
    private String address;
    private Map<Long, Event> planning = new ConcurrentHashMap<>();

    public void setIdClient(long idClient) {
        this.idClient = idClient;
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
        return this.idClient;
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
                "idCliente = " + this.idClient + "\n" +
                "Nombre : " + this.name + "\n" +
                "Apellido : " + this.surname + "\n" +
                "Email : " + this.email + "\n" +
                "Telefono : " + this.phoneNumber + "\n" +
                "Password : " + this.passwd + "\n" +
                "Direccion : " + this.address + "\n";
    }

    public void addPlanning (Event e){
        if (!this.planning.containsKey(e.getId())){
            this.planning.put(e.getId(),e);
        }
        else{
            //no se que poner aqui, un error??
        }
    }
/*
    public void addToPlanning(Event e){
        Event inn = inPlanning(e);
        this.planning.put(inn.getId(), inn);
    }

    public Map<Long, Event> getPlanning(){
        return this.planning;
    }

    public void deleteEvent(Event r){
        Event inn = inPlanning(r);
        if(inn != null){
            this.planning.remove(inn);
        }
    }

    public void cleanEvent(){
        this.planning.clear();
    }

    public void updatePlanningInOne(Event r){
        Event inn = inPlanning(r);
        if(inn != null){
            this.planning.put(inn, this.planning.get(inn));
        }
    }
    public void updatePlanningInOneLess(Event r){
        Event inn = inPlanning(r);
        if(inn != null){
            this.planning.put(inn, this.planning.get(inn));
        }
    }

    public  getPlanningEventNumber(Event e){
        Event inn = inPlanning(e);
        return this.planning.get(inn);
    }

    public boolean containsPlanning(Event e){
        Event inn = inPlanning(e);
        return inn != null;
    }

    private Event inPlanning(Event e1){
        for(Event e : this.planning.keySet()){
            if(e.getId() == e1.getId()) return e;
        }
        return null;
    }

 */
}
