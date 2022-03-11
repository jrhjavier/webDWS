package com.dws.web;

import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Event {
    private long idEvent;
    private String name;
    private String description;
    private int price;
    private String category;
    //private int averageRating = 0;

    /*
    private Map<Long, Review> reviews = new ConcurrentHashMap<>();
*/
    public Event() {
    }

    public Event(String name, String category, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public int getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    /*public int getStarsAverage() {
        return this.averageRating;
    }

     */

    public long incrementAndGetId() {  //el increment no sabemos por que es, habra que incrementar algo
        return this.idEvent++;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /*public void setStarsAverage(int averageRating) {
        this.averageRating = averageRating;
    }

     */

    public long getIdEvent() {
        return idEvent;
    }

    /*public int getAverageRating() {
        return averageRating;
    }

     */

    /*public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

     */

    /*
    public Collection<Review> getReviews() {
        return this.reviews.values();
    }

    public void setReviews(Map<Long, Review> reviews) {
        this.reviews = reviews;
    }

    public void setReview (Review r){
        this.reviews.put(r.getIdReview(), r);
    }

    public Review getReview(Review r){
        return this.reviews.get(r.getIdReview());
    }

    public Review getReview(long id){
        return this.reviews.get(id);
    }

     */
    @Override
    public String toString() {
        return "Sitio:" + "\n" + "IdSitio: " + this.idEvent + "\n" + "name: " +
                this.name + "\n" + "description: " + this.description + "\n" +
                "price: " + this.price + "\n";
    }

    public boolean sameCategory(String category) {
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

    /*

    public Review eventContainsReview(Review r1){

        for (Review r : this.reviews.values()){
            if (r1.equals(r)){
                return r;
            }
        }

        return null;
    }

    public void cleanReviews(){
        this.reviews.clear();
    }

    public void deleteReviewOfAnEvent(Review r){
        this.reviews.remove(r.getIdReview());
    }


     */

}
