package com.example.DinerReviewAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;


// TODO: Define a plain ol' Java object (POJO) class called Restaurant. This class should have a property that represents the unique database id, the type can be Long.
@Entity
@Table(name = "RESTAURANTS")
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "PEANUT_ALLERGY_SCORE")
    private Integer peanutAllergyScore;

    @Column(name = "EGG_ALLERGY_SCORE")
    private Integer eggAllergyScore;

    @Column(name = "DAIRY_ALLERGY_SCORE")
    private Integer dairyAllergyScore;

    @Column(name = "PRICE")
    private String price;

    @Column(name = "HOURS")
    private String hours;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "RATING")
    private Integer rating;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address, String phoneNumber, Integer peanutAllergyScore, Integer eggAllergyScore, Integer dairyAllergyScore, String price, String hours, String image, Integer rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.peanutAllergyScore = peanutAllergyScore;
        this.eggAllergyScore = eggAllergyScore;
        this.dairyAllergyScore = dairyAllergyScore;
        this.price = price;
        this.hours = hours;
        this.image = image;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPeanutAllergyScore() {
        return peanutAllergyScore;
    }

    public void setPeanutAllergyScore(Integer peanutAllergyScore) {
        this.peanutAllergyScore = peanutAllergyScore;
    }

    public Integer getEggAllergyScore() {
        return eggAllergyScore;
    }

    public void setEggAllergyScore(Integer eggAllergyScore) {
        this.eggAllergyScore = eggAllergyScore;
    }

    public Integer getDairyAllergyScore() {
        return dairyAllergyScore;
    }

    public void setDairyAllergyScore(Integer dairyAllergyScore) {
        this.dairyAllergyScore = dairyAllergyScore;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
