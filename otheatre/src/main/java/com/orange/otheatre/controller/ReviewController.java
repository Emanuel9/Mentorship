package com.orange.otheatre.controller;

import com.orange.otheatre.entities.*;
import com.orange.otheatre.model.Mark;
import com.orange.otheatre.repositories.EventRepository;
import com.orange.otheatre.repositories.UserProfileRepository;
import com.orange.otheatre.repositories.UserRepository;
import com.orange.otheatre.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ReviewController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    ReviewService reviewService;


    @RequestMapping(method = RequestMethod.GET,value = "/review/{eventId}")
    public String viewReviewedEvent(@PathVariable(value="eventId") String eventId,
                                    Model model,
                                    HttpServletRequest request){
        LOGGER.info("ReviewPage: Welcome to Review Page!");

        Optional<Event> optionalEvent = eventRepository.findById(Long.parseLong(eventId));
        Event event = optionalEvent.get();
        model.addAttribute("event",event);

        UserProfile userProfile = new UserProfile();

        try {
            String email = request.getUserPrincipal().getName();
            Optional<User> optionalPrincipal = userRepository.findByEmail(email);
            User principal = optionalPrincipal.get();

            Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(principal);
            if(optionalUserProfile.isPresent()){
                userProfile = optionalUserProfile.get();
                LOGGER.info("ReviewPage: Found authenticated user: "+ userProfile.getFirstName());
            }
        } catch ( Exception ex ) {
            LOGGER.debug("ReviewPage: " + ex.getMessage());
        }

        model.addAttribute("userProfile",userProfile);
        model.addAttribute("reviewToAdd", new Review());

        return "reviewPage";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/review/addReview/{eventId}")
    public String addReview (@PathVariable(value="eventId") String eventId,
                             @ModelAttribute Review reviewToAdd,
                             @RequestParam (value = "mark") String mark,
                             HttpServletRequest request,
                             Model model){

        Optional<Event> optionalEvent = eventRepository.findById(Long.parseLong(eventId));
        Event event = optionalEvent.get();

        UserProfile userProfile = new UserProfile();

        try {
            String email = request.getUserPrincipal().getName();
            Optional<User> optionalPrincipal = userRepository.findByEmail(email);
            User principal = optionalPrincipal.get();

            Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(principal);
            if(optionalUserProfile.isPresent()){
                userProfile = optionalUserProfile.get();
                LOGGER.info("ReviewPage: Found authenticated user: "+ userProfile.getFirstName());
            }
        } catch ( Exception ex ) {
            LOGGER.debug("ReviewPage: " + ex.getMessage());
        }

        reviewToAdd.setEvent(event);
        reviewToAdd.setReviewCreator(userProfile);

        try{
            reviewService.saveReview(reviewToAdd,mark);
        }catch( Exception ex ){
            LOGGER.error( "ReviewPage: " + ex.getMessage() );

            return "redirect:/event/"+event.getEventId().toString();
        }

        return "redirect:/event/"+event.getEventId().toString();
    }
}
