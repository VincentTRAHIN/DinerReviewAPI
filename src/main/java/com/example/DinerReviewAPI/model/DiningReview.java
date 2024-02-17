package com.example.DinerReviewAPI.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class DiningReview {
    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;
    private String reviewerName;
    private String review;
    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;
    private String status;

}
