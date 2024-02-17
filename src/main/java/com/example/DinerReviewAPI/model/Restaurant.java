package com.example.DinerReviewAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.Data;


// This is the Restaurant class. It is an entity class that maps to the RESTAURANTS table in the database.
@Entity
@Table(name = "RESTAURANTS")
@Data
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

    
}
