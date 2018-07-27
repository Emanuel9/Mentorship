package com.orange.otheatre.controller;

import com.orange.otheatre.entities.*;
import com.orange.otheatre.repositories.EventRepository;
import com.orange.otheatre.repositories.UserProfileRepository;
import com.orange.otheatre.repositories.UserRepository;
import com.orange.otheatre.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    EventService eventService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET,value = "/event/{eventId}")
    public String viewEvent (@PathVariable(value="eventId") String eventId,
                             Model model,
                             HttpServletRequest request){
        LOGGER.info("Event: Welocome to EventController. I am now going to show information about event id " + eventId);
        LOGGER.info("Event: Checking if event id is null.");

        Event event;
        List<Event> eventList;
        List<Review> reviewList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        boolean isEligible = false;

        if(eventId != null){
            LOGGER.info("Event: Event id is not null. Searching event in database.");
            Optional<Event> optionalEvent = eventRepository.findById(Long.parseLong(eventId));
            event = optionalEvent.get();
            model.addAttribute("event",event);
            LOGGER.info("Event: Event entitled {} was put in model.", event.getEventTitle());

            LOGGER.info("Event: Searching for all events entitled: " + event.getEventTitle());
            eventList = eventRepository.findAllByEventTitle(event.getEventTitle());
            LOGGER.info("Event: Found {} events.", eventList.size());

            LOGGER.info("Event: Searching for reviews and comments from all events entitled: " + event.getEventTitle());
            for(Event e : eventList){
                reviewList.addAll(e.getEventReviews());
                commentList.addAll(e.getEventComments());
            }

            LOGGER.info("Event: Found {} reviews and {} comments ", reviewList.size(), commentList.size());

            UserProfile userProfile = null;
            if(SecurityContextHolder.getContext().getAuthentication() != null &&
                    SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                    //when Anonymous Authentication is enabled
                    !(SecurityContextHolder.getContext().getAuthentication()
                            instanceof AnonymousAuthenticationToken)){

                try {
                    String email = request.getUserPrincipal().getName();
                    Optional<User> optionalPrincipal = userRepository.findByEmail(email);
                    User principal = optionalPrincipal.get();

                    Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(principal);
                    if(optionalUserProfile.isPresent()){
                        userProfile = optionalUserProfile.get();
                        LOGGER.info("EventPage: Found authenticated user: "+ userProfile.getFirstName());
                    }
                } catch ( Exception ex ) {
                    LOGGER.debug("EventPage: " + ex.getMessage());
                }

                LOGGER.info("Event: Checking is conditions for review link are met.");
                 userProfile.setEventsAttended(Arrays.asList(event)); /*for testing only*/
//               userProfileRepository.saveAndFlush(userProfile); /*for testing only*/
                isEligible = eventService.isEligibileForReview(userProfile, event);

                if(isEligible){
                    LOGGER.info("Event: Conditions for review link are met.");
                }

            }else{
                isEligible = false;
            }

            model.addAttribute("reviews", reviewList);
            model.addAttribute("comments", commentList);
            model.addAttribute("displayReview",isEligible);

            LOGGER.info("Event: Added reviews and comments to model.");

        }
        return "event";
    }

}
