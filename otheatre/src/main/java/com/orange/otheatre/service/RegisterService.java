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

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurePassword securePassword;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    public User addUser(User user) {
        LOGGER.info("RegisterService: Checking if user {} exists.", user.getEmail());
        if( userRepository.findByEmail(user.getEmail()).isPresent() ){
            LOGGER.info("RegisterService: User {} already exists in the database. Returning the user from the database.", user.getEmail());
            return userRepository.findByEmail(user.getEmail()).get();
        }

        LOGGER.info("RegisterService:: Checking if user {} has a role.", user.getEmail());
        if( user.getRole() == null ){
            LOGGER.info("RegisterService: User {} doesn't have a role. Setting role to PARTICIPANT.", user.getEmail());
            user.setRole(UserRole.PARTICIPANT);
        }
        LOGGER.info("RegisterService: User {} has the {} role", user.getEmail(), user.getRole().toString());

        user.setPassword( securePassword.passwordEncoder().encode(user.getPassword()) );

        try {
            userService.saveUser(user);
            LOGGER.info("RegisterService: Successfully created user {}", user.getEmail());
        }catch (Exception ex){
            LOGGER.error("RegisterService - save user: " + ex.getMessage());
        }finally {
            LOGGER.info("RegisterService: Checking if user {} has an user profile", user.getEmail());
            if(!userProfileRepository.findByUser(user).isPresent()){
                LOGGER.info("RegisterService: User {} doesn't have an user profile. Assigning an empty one.", user.getEmail());
                UserProfile userProfile = new UserProfile();
                userProfile.setUser(user);

                userProfileService.saveUserProfile(userProfile);
            }
        }

        return user;
    }
}
