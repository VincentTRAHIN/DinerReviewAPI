package com.example.DinerReviewAPI.repository;

import com.example.DinerReviewAPI.model.DiningReview;
import com.example.DinerReviewAPI.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    List<DiningReview> findDiningReviewsByRestaurantIdAndStatus(Long restaurantId, ReviewStatus diningReviewStatus);
    List<DiningReview> findDiningReviewsByStatus(ReviewStatus diningReviewStatus);
}
