package com.orange.otheatre.controller;

import com.orange.otheatre.entities.Event;
import com.orange.otheatre.entities.User;
import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.repositories.UserProfileRepository;
import com.orange.otheatre.repositories.UserRepository;
import com.orange.otheatre.service.UserProfileService;
import com.orange.otheatre.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserProfileController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public String profilePage( Model model ){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        LOGGER.info("UserProfile: Checking if user is authenticated.");
        User user = null;
        UserProfile userProfile = null;
        if ( authentication != null ) {
            user = (User) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }
            model.addAttribute("user", user);
            LOGGER.info("UserProfile: User authenticated with authentication {}", user.getEmail());
        }

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(user);

        if(optionalUserProfile.isPresent()){
            userProfile = optionalUserProfile.get();
        }else{
            userProfile = new UserProfile();
            userProfile.setUser(user);
        }
        model.addAttribute("profileToEdit", userProfile);

        LOGGER.info("UserProfile: Displaying user profile.");
        return "profile";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/profile/edit")
    public String editProfilePage( Model model ){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        LOGGER.info("UserProfile: Checking if user is authenticated.");
        User user = null;
        UserProfile userProfile = null;
        if ( authentication != null ) {
            user = (User) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }
            model.addAttribute("user", user);
            LOGGER.info("UserProfile: User authenticated with authentication {}", user.getEmail());
        }

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(user);

        if(optionalUserProfile.isPresent()){
            userProfile = optionalUserProfile.get();
        }else{
            userProfile = new UserProfile();
            userProfile.setUser(user);
        }
        model.addAttribute("profileToEdit", userProfile);

        LOGGER.info("UserProfile: Displaying user profile.");
        return "editProfile";

    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/profile/edit")
    public String editUserProfile(@ModelAttribute UserProfile profileToEdit){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        User user = null;
        if ( authentication != null ) {
            user = (User) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(user);
        UserProfile userProfile = optionalUserProfile.get();

        userProfile.setFirstName(profileToEdit.getFirstName());
        userProfile.setLastName(profileToEdit.getLastName());
        userProfile.setBio(profileToEdit.getBio());
        userProfile.setBirthday(profileToEdit.getBirthday());

//        profileToEdit.setUser(user);
        try {
            userProfileService.saveUserProfile(userProfile);
        }catch(Exception ex){
            LOGGER.error("UserProfile: " + ex.getMessage());
        }
        return "profile";
    }

}
