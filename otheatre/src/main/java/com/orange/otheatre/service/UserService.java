package com.orange.otheatre.service;

import com.orange.otheatre.entities.User;
import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.model.UserRole;
import com.orange.otheatre.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SecurePassword securePassword;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public User saveUser(User user){
        LOGGER.info("UserService: Checking if user {} exists.", user.getEmail());
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if( userOptional.isPresent() ){
            LOGGER.info("UserService: User {} already exists in the database. Returning the user from the database.", user.getEmail());
            return userOptional.get();
        }

        LOGGER.info("UserService: Checking if user {} has a role.", user.getEmail());
        if( user.getRole() == null ){
            LOGGER.info("UserService: User {} doesn't have a role. Setting role to PARTICIPANT.", user.getEmail());
            user.setRole(UserRole.PARTICIPANT);
        }
        LOGGER.info("UserService: User {} has the {} role", user.getEmail(), user.getRole().toString());

        user.setPassword( securePassword.passwordEncoder().encode(user.getPassword()) );

        try {
            user = userRepository.saveAndFlush(user);
            LOGGER.info("UserService: Successfully created user {}", user.getEmail());
        }catch (Exception ex){
            LOGGER.error("UserService - save user: " + ex.getMessage());
        }

        return user;
    }
}
