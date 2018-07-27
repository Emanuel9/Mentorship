package com.orange.otheatre.controller;

import com.orange.otheatre.entities.Event;
import com.orange.otheatre.entities.Hall;
import com.orange.otheatre.entities.User;
import com.orange.otheatre.model.UserRole;
import com.orange.otheatre.repositories.EventRepository;
import com.orange.otheatre.repositories.HallRepository;
import com.orange.otheatre.repositories.UserRepository;
import com.orange.otheatre.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/manageEvents")
public class ManageEventsController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    EventService saveEvent;

    @RequestMapping(method = RequestMethod.GET)
    public String manageEventsPage(HttpServletRequest request, Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        String email = request.getUserPrincipal().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " does not exist!"));
        UserRole userRole = user.getRole();

        model.addAttribute("eventToAdd", new Event());
        model.addAttribute("hallsList", hallRepository.findAll());

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            model.addAttribute("principal", principal);
        }

        if (userRole.equals(UserRole.ADMIN)) {
            model.addAttribute("events", eventRepository.findAll());
            return "manageEvents";
        }

        if (userRole.equals(UserRole.PLAY_ORGANIZER)) {
            model.addAttribute("events", eventRepository.findAllByEventOwner(user));
            return "manageEvents";
        }

        return "redirect:/access-denied";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addEvent")
    public String addEventSubmit(HttpServletRequest request,
                                 @ModelAttribute Event eventToAdd,
                                 @RequestParam(value="eventHall") String hallName) {
        if( hallName != null ){
            List<Hall> halls = new ArrayList<>();
            Hall hall = hallRepository.findByHallName( hallName );
            if ( hall != null ) {
                halls.add(hall);
                eventToAdd.setHalls(halls);
            }
        }

        if ( request.getUserPrincipal() != null ) {
            String email = request.getUserPrincipal().getName();
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " does not exist!"));
            eventToAdd.setEventOwner(user);
        }

        try {
             saveEvent.saveEvent(eventToAdd);
        } catch ( Exception ex ) {
            LOGGER.error(ex.getMessage());
        }

        return "redirect:/manageEvents";
    }

}
