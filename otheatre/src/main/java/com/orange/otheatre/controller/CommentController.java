package com.orange.otheatre.controller;

import com.orange.otheatre.entities.Comment;
import com.orange.otheatre.entities.Event;
import com.orange.otheatre.entities.User;
import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.repositories.EventRepository;
import com.orange.otheatre.repositories.UserProfileRepository;
import com.orange.otheatre.repositories.UserRepository;
import com.orange.otheatre.service.CommentService;
import com.orange.otheatre.service.UserProfileService;
import com.orange.otheatre.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class CommentController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    UserProfileService userProfileService;

    Event event;

    UserProfile userProfile;

    @RequestMapping(method = RequestMethod.GET,value = "/comment/{eventId}")
    public String viewCommentedEvent(@PathVariable(value="eventId") String eventId,
                                     Model model,
                                     HttpServletRequest request){

        LOGGER.info("Comment: Welcome to CommentController. I am identifying the event that will be commented. It has the id:  " + eventId);
        Optional<Event> optionalEvent = eventRepository.findById(Long.parseLong(eventId));
        event = optionalEvent.get();
        model.addAttribute("event",event);

        if ( SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ) {
            LOGGER.info("CommentPage: Checking if user is authenticated.");
            try {
                String email = request.getUserPrincipal().getName();
                Optional<User> optionalPrincipal = userRepository.findByEmail(email);
                User principal = optionalPrincipal.get();

                Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(principal);
                if(optionalUserProfile.isPresent()){
                    userProfile = optionalUserProfile.get();
                    LOGGER.info("CommentPage: Found authenticated user: "+ userProfile.getFirstName());
                    if(userProfile.getFirstName()== null){
                        userProfile = userProfileService.showAnonymousName();
                    }
                }else{
                    userProfile = userProfileService.showAnonymousName();
                }
            } catch ( Exception ex ) {
                LOGGER.error("CommentPage: " + ex.getMessage());
            }
        }else{
            userProfile = userProfileService.showAnonymousName();
        }
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("commentToAdd", new Comment());
        return "commentPage";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/comment/addComment")
    public String addComment(@ModelAttribute Comment commentToAdd){

        commentToAdd.setCommentCreator(userProfile);
        commentToAdd.setEvent(event);

        try{
            commentService.saveComment(commentToAdd);
        }catch ( Exception ex ) {
            LOGGER.error("CommentPage: " + ex.getMessage());
        }
        LOGGER.info("CommentPage: A new comment for the play {} was added by {}.", event.getEventTitle(),userProfile.getFirstName());
        return "redirect:/event/"+event.getEventId();
    }

}
