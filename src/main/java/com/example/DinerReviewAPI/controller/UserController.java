package com.example.DinerReviewAPI.controller;

import com.example.DinerReviewAPI.model.User;
import com.example.DinerReviewAPI.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        validateUser(user);
         userRepository.save(user);
    }

    @GetMapping("/{userName}")
    public User getUserByUserName(@PathVariable String userName) {
        validateUserName(userName);

        Optional<User> OptionalUser = userRepository.findByUsername(userName);
        if (!OptionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User existingUser = OptionalUser.get();
        existingUser.setId(null);
        return existingUser;
    }

    @PutMapping("/{userName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String userName, @RequestBody User updatedUser) {
        validateUserName(userName);

        Optional<User> optionalUser = userRepository.findByUsername(userName);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User existingUser = optionalUser.get();
        copyUserInfoForm(updatedUser, existingUser);
        userRepository.save(existingUser);
    }

    private void copyUserInfoForm(User updatedUser, User existingUser) {
        if (ObjectUtils.isEmpty(updatedUser.getUsername())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (!ObjectUtils.isEmpty(updatedUser.getCity())) {
            existingUser.setCity(updatedUser.getCity());
        }
        if (!ObjectUtils.isEmpty(updatedUser.getState())) {
            existingUser.setState(updatedUser.getState());
        }
        if (!ObjectUtils.isEmpty(updatedUser.getZipCode())) {
            existingUser.setZipCode(updatedUser.getZipCode());
        }
        if (!ObjectUtils.isEmpty(updatedUser.getPeanutAllergy())) {
            existingUser.setPeanutAllergy(updatedUser.getPeanutAllergy());
        }
        if (!ObjectUtils.isEmpty(updatedUser.getEggAllergy())) {
            existingUser.setEggAllergy(updatedUser.getEggAllergy());
        }
        if (!ObjectUtils.isEmpty(updatedUser.getDairyAllergy())) {
            existingUser.setDairyAllergy(updatedUser.getDairyAllergy());
        }
    }

    private void validateUser(User user) {
        validateUserName(user.getUsername());

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateUserName(String userName) {
        if (ObjectUtils.isEmpty(userName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    } 
    

    


    
}
