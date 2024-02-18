package com.example.DinerReviewAPI.controller;

import com.example.DinerReviewAPI.model.DiningReview;
import com.example.DinerReviewAPI.model.ReviewStatus;
import com.example.DinerReviewAPI.model.Restaurant;
import com.example.DinerReviewAPI.model.User;
import com.example.DinerReviewAPI.repository.RestaurantRepository;
import com.example.DinerReviewAPI.repository.UserRepository;
import com.example.DinerReviewAPI.repository.DiningReviewRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.util.ObjectUtils;



import java.util.Optional;

@RequestMapping("/diningReviews")
@RestController
public class DiningReviewController {
    private final DiningReviewRepository diningReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public DiningReviewController(DiningReviewRepository diningReviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDiningReview(@RequestBody DiningReview diningReview) {
        validateDiningReview(diningReview);

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(diningReview.getRestaurantId());
        if (optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        diningReview.setStatus(ReviewStatus.PENDING);
        diningReviewRepository.save(diningReview);
    }

    private void validateDiningReview(DiningReview diningReview) {
        if (ObjectUtils.isEmpty(diningReview.getSubmittedBy())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (ObjectUtils.isEmpty(diningReview.getRestaurantId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (ObjectUtils.isEmpty(diningReview.getPeanutScore()) && ObjectUtils.isEmpty(diningReview.getDairyScore()) && ObjectUtils.isEmpty(diningReview.getEggScore())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = userRepository.findByUsername(diningReview.getSubmittedBy());
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }



    
}
