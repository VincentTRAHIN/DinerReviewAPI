package com.example.DinerReviewAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.Data;

@Entity
@Table(name = "DINING_REVIEWS")
@Data
public class DiningReview {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "RESTAURANT_ID")
    private Long restaurantId;

    @Column(name = "REVIEWER_NAME")
    private String reviewerName;

    @Column(name = "PEANUT_SCORE")
    private Integer peanutScore;
    
    @Column(name = "EGG_SCORE")
    private Integer eggScore;

    @Column(name = "DAIRY_SCORE")
    private Integer dairyScore;

    
}
