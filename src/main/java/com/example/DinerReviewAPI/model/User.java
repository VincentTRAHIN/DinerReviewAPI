package com.example.DinerReviewAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.Data;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "PEANUT_ALLERGY")
    private Boolean peanutAllergy;

    @Column(name = "EGG_ALLERGY")
    private Boolean eggAllergy;

    @Column(name = "DAIRY_ALLERGY")
    private Boolean dairyAllergy;
    
}
