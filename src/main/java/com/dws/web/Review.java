package com.dws.web;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class Review {

    interface Basico {}

    @JsonView(Customer.Basico.class)
    private long idReview;

    @JsonView(Customer.Basico.class)
    private String userName;

    @JsonView(Customer.Basico.class)
    private String message;

    public Review(){
    }

    public Review(long idReview, String userName, String message){
        this.idReview=idReview;
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
}
