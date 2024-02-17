package com.example.DinerReviewAPI.repository;

import com.example.DinerReviewAPI.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

