package com.orange.otheatre.service;

import com.orange.otheatre.entities.*;
import com.orange.otheatre.repositories.CommentRepository;
import com.orange.otheatre.repositories.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public UserProfile saveUserProfile(UserProfile userProfile){

        Optional<UserProfile> optionalUserProfile = Optional.empty();
        optionalUserProfile = checkIfProfileExists(userProfile);
        if(optionalUserProfile.isPresent()){
            LOGGER.info("User Profile Service: User {} already has a profile in the database. Returning profile from database.",userProfile.getUser().getEmail());
            return optionalUserProfile.get();

        }
        LOGGER.info("User Profile Service: Creating and returning the new profile for user {}.",userProfile.getUser().getEmail());
        return userProfileRepository.saveAndFlush(userProfile);
    }
<<<<<<< HEAD

    public UserProfile updateUserProfile (UserProfile userProfile) throws Exception {

        Optional<UserProfile> optionalUserProfile = Optional.empty();
        optionalUserProfile = checkIfProfileExists(userProfile);

        if(!optionalUserProfile.isPresent()){
            throw new Exception("Cannot update a profile that does not exist!");
        }
        LOGGER.info("User Profile Service: Returning the updated profile for user {}.",userProfile.getUser().getEmail());
        return userProfileRepository.saveAndFlush(userProfile);
    }

    private Optional<UserProfile> checkIfProfileExists(UserProfile userProfile){
        LOGGER.info("User Profile Service: Checking if profile for user {} exists",userProfile.getUser().getEmail());
        return userProfileRepository.findByUser(userProfile.getUser());
    }

    public Optional<UserProfile> checkIfProfileExists(User user){
        LOGGER.info("User Profile Service: Checking if profile for user {} exists",user.getEmail());
        return userProfileRepository.findByUser(user);
    }

    public UserProfile createNewUserProfile(User user){
        LOGGER.info("User Profile Service: Creating a new profile for user {}.",user.getEmail());
        UserProfile userProfile = new UserProfile();

        LOGGER.info("User Profile Service: Associating the user {} to the new profile.",user.getEmail());
        userProfile.setUser(user);

        LOGGER.info("User Profile Service: Adding an empty comments list to the new profile.");
        userProfile.setComments(new ArrayList<Comment>());

        LOGGER.info("User Profile Service: Adding an empty reviews list to the new profile.");
        userProfile.setReviews(new ArrayList<Review>());

        LOGGER.info("User Profile Service: Adding an empty events list to the new profile.");
        userProfile.setEventsAttended(new ArrayList<Event>());

        LOGGER.info("User Profile Service: Returning profile for user {}",user.getEmail());
        return saveUserProfile(userProfile);
    }
=======
>>>>>>> Minor adjustments
}
