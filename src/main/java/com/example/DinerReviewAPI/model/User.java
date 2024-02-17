package com.example.DinerReviewAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String city;
    private String state;
    private Boolean peanutAllergy;
    private Boolean eggAllergy;
    private Boolean dairyAllergy;
    
}
