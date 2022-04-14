package com.dws.web.Customer;

import com.dws.web.Event.Event;
import com.dws.web.Review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@NoArgsConstructor
@Table(name = "Customer")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@JsonView(Basico.class)
    private long idCustomer;

    //@JsonView(Basico.class)
    private String name;

    //@JsonView(Basico.class)
    private String surname;

    //@JsonView(Basico.class)
    private String email;

    //@JsonView(Basico.class)
    private String phoneNumber;

    // @JsonView(Basico.class)
    private String passwd;

    //@JsonView(Basico.class)
    private String address;

    @JsonIgnore
    @ManyToMany
    private List<Event> planning;

    @JsonIgnore
    @OneToMany
    private List<Review> reviews;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public Customer(String name, String surname, String email, String phoneNumber, String passwd, String address){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwd = passwd;
        this.address = address;
        this.roles.add("ROLE_USER");
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

    public List<String> getRoles() {
        return this.roles;
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

    public boolean getAdmin(){
        for(String role : roles){
            if(role.equals("ROLE_ADMIN"))return true;
        }
        return false;
    }

    public void makeAdmin(){
        this.roles.add("ROLE_ADMIN");
    }

    //PLANNING

    public boolean addToPlanning(Event e){
        if (!this.planning.contains(e)){

            this.planning.add(e);
            return true;
        }
        else{
            return false;
        }
    }

    public List<Event> getPlanning(){
        return this.planning;
    }

    public void deleteEvent(long idEvent){
        for (Event e: this.planning){
            if (e.getId()==idEvent){
                this.planning.remove(e);
            }
        }
    }

    public void cleanEvent(){
        this.planning.clear();
    }


    public boolean containsPlanning(Event e1){
        Event e = inPlanning(e1);
        return e != null;
    }

    public Event inPlanning(Event e1){
        for(Event e : this.planning){
            if(e.getId() == e1.getId()) return e;
        }
        return null;
    }

    public List<Event> getAllEvents(){
        return this.planning;
    }

    public Event getAnEvent(long idEvent){
        for (Event e:this.planning){
            if (e.getId()==idEvent){
                return e;
            }
        }
        return null;
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


    /*
    @ManyToMany
    @JoinTable(
            name="events_Customer",
            joinColumns = @JoinColumn(name = "Customer_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    //private Set<Map.Entry<Long, Event>> eventsCustomer = this.planning.entrySet();
    private Set<Event> eventsCustomer = new HashSet<>();

     */
}
