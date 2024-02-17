package com.example.DinerReviewAPI.repository;

import com.example.DinerReviewAPI.model.DiningReview;
import org.springframework.data.repository.CrudRepository;


public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    //get the list of all dinig reviews that are pending 
    Iterable<DiningReview> findByStatus(String status);
    //get the set of all approved dinging reviews belonging to a specific restaurant 
    Iterable<DiningReview> findByRestaurantIdAndStatus(Long restaurantId, String status);
}
