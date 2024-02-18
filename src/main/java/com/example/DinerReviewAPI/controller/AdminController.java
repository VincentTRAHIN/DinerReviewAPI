package com.example.DinerReviewAPI.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.DinerReviewAPI.repository.UserRepository;
import com.example.DinerReviewAPI.model.AdminReviewAction;
import com.example.DinerReviewAPI.model.DiningReview;
import com.example.DinerReviewAPI.model.ReviewStatus;
import com.example.DinerReviewAPI.repository.DiningReviewRepository;
import com.example.DinerReviewAPI.model.Restaurant;
import com.example.DinerReviewAPI.repository.RestaurantRepository;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;


@RequestMapping("/admin")
@RestController
public class AdminController {
    private final UserRepository userRepository;
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(UserRepository userRepository, DiningReviewRepository diningReviewRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/reviews")
    public List<DiningReview> findDiningReviews( @RequestParam String status) {
        ReviewStatus reviewStatus = ReviewStatus.PENDING;
        try {
            reviewStatus = ReviewStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return diningReviewRepository.findDiningReviewsByStatus(reviewStatus);
    }

    @PutMapping("/reviews/{id}")
    public void performReviewAction(@PathVariable Long id, @RequestBody AdminReviewAction adminReviewAction) {
        Optional<DiningReview> OptionalDiningReview = diningReviewRepository.findById(id);
        if (OptionalDiningReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        DiningReview diningReview = OptionalDiningReview.get();

        Optional <Restaurant> OptionalRestaurant = restaurantRepository.findById(diningReview.getRestaurantId());
        if (OptionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (adminReviewAction.getAccept()) {
            diningReview.setStatus(ReviewStatus.ACCEPTED);
        }
        else {
            diningReview.setStatus(ReviewStatus.REJECTED);
        }

        diningReviewRepository.save(diningReview);
        updateRestaurantOverallScore(OptionalRestaurant.get());
    
}
    
        private void updateRestaurantOverallScore(Restaurant restaurant) {
            List<DiningReview> diningReviews = diningReviewRepository.findDiningReviewsByRestaurantIdAndStatus(restaurant.getId(), ReviewStatus.ACCEPTED);
            if (diningReviews.size() == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            int peanutSum = 0;
            int peanutCount = 0;
            int dairySum = 0;
            int dairyCount = 0;
            int eggSum = 0;
            int eggCount = 0;

            for (DiningReview diningReview : diningReviews) {
                if (!ObjectUtils.isEmpty(diningReview.getPeanutScore())) {
                    peanutSum += diningReview.getPeanutScore();
                    peanutCount++;
                }
                if (!ObjectUtils.isEmpty(diningReview.getDairyScore())) {
                    dairySum += diningReview.getDairyScore();
                    dairyCount++;
                }
                if (!ObjectUtils.isEmpty(diningReview.getEggScore())) {
                    eggSum += diningReview.getEggScore();
                    eggCount++;
                }
            
            }

            int totalCount = peanutCount + dairyCount + eggCount;
            int totalSum = peanutSum + dairySum + eggSum;

            float overallScore = (float) totalSum / totalCount;
            restaurant.setOverallScore(String.format("%.2f", overallScore));    
            if (peanutCount > 0) {
                float peanutScore = (float) peanutSum / peanutCount;
                restaurant.setPeanutScore(String.format("%.2f", (float) peanutScore));
            }
            if (dairyCount > 0) {
                float dairyScore = (float) dairySum / dairyCount;
                restaurant.setDairyScore(String.format("%.2f", (float) dairyScore));
            }
            if (eggCount > 0) {
                float eggScore = (float) eggSum / eggCount;
                restaurant.setEggScore(String.format("%.2f", (float) eggScore));
            }

            restaurantRepository.save(restaurant);
        }

}