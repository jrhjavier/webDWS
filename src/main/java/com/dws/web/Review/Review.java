package com.dws.web.Review;

import com.dws.web.Customer.Customer;
import com.dws.web.Event.Event;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonView(Customer.Basico.class)
    private long idReview;

    //@JsonView(Customer.Basico.class)
    private String userName;

    //@JsonView(Customer.Basico.class)
    private String message;

    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Event event;


    public Review(String userName, String message){
        this.userName=userName;
        this.message=message;
    }

    public long getIdReview() {
        return this.idReview;
    }

    public void setIdReview(long idReview) {
        this.idReview = idReview;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Review{" +
                "idReview=" + idReview +
                ", userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public void assignEvent(Event e){
        this.event=e;
    }

    public void unassignEvent(Event e){
        this.event=null;
    }

    public Event getEvent(){
        return this.event;
    }
}
