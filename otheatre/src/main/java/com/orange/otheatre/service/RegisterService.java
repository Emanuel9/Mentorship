/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.service;

import com.orange.otheatre.entities.User;
import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.model.UserRole;
import com.orange.otheatre.repositories.UserProfileRepository;
import com.orange.otheatre.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marius.Herta
 */
@Service
public class RegisterService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

   private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    public User addUser(User user) {
        LOGGER.info("Register service: Registering the new user {}.", user.getEmail());
        User userToBeReturned = new User();
        userToBeReturned = userService.saveUser(user);
        userProfileService.createNewUserProfile(userToBeReturned);
        LOGGER.info("Register service: Registration for user {} is successful.", userToBeReturned.getEmail());
        return userToBeReturned;
    }
}
