package com.orange.otheatre.service;

import com.orange.otheatre.entities.User;
import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.repositories.CommentRepository;
import com.orange.otheatre.repositories.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public UserProfile saveUserProfile(UserProfile userProfile){
        return userProfileRepository.saveAndFlush(userProfile);
    }

    public UserProfile showAnonymousName() {
        Optional<UserProfile> optionalUserProfile2 = userProfileRepository.findByFirstNameAndLastName("Anonymous", "Anonymous");
        UserProfile userProfile = new UserProfile();
        if (optionalUserProfile2.isPresent()) {
            userProfile = optionalUserProfile2.get();
            LOGGER.info("CommentPage: No authenticated user found, adding comment as Anonymous form database");
        } else {
            userProfile = new UserProfile();
            User user = new User();
            user.setEmail("dummy@dummy.com");
            user.setPassword(" ");
            userProfile.setFirstName("Anonymous");
            userProfile.setLastName("Anonymous");
            userProfile.setUser(user);
            userService.saveUser(user);
            userProfileService.saveUserProfile(userProfile);
            LOGGER.info("CommentPage: No authenticated user found, creating an Anonymous user.");
        }
        return userProfile;
    }
}
