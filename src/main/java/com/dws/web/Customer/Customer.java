package com.dws.web.Customer;

import com.dws.web.Event.Event;
import com.dws.web.Review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@NoArgsConstructor

public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(mappedBy = "customers")
    private List<Event> planning;

    public Customer(String name, String surname, String email, String phoneNumber, String passwd, String address){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwd=passwd;
        //this.passwd = new BCryptPasswordEncoder().encode(passwd);
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

    public void setAddress(String address) {
        this.address = address;
    }

    /*
    public void setPassword(String passwd) {
        this.passwd = codify(passwd);
    }

    public String codify(String passwd){
        String pass = new BCryptPasswordEncoder().encode(passwd);
        return pass;
    }

     */

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

    public void deleteEvent(Event event){
        this.planning.remove(event);
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

    public Collection<Event> getAllEvents(){
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


    //No la ponemos aún:
    /*
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    //@JoinColumn(name = "cr_fk", referencedColumnName = "id")
    private Set<Review> reviews;

     */

}
