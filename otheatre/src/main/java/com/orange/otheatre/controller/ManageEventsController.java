package com.orange.otheatre.controller;

import com.orange.otheatre.entities.Event;
import com.orange.otheatre.entities.Hall;
import com.orange.otheatre.entities.User;
import com.orange.otheatre.model.UserRole;
import com.orange.otheatre.repositories.EventRepository;
import com.orange.otheatre.repositories.HallRepository;
import com.orange.otheatre.repositories.UserRepository;
import com.orange.otheatre.otheatre.service.CustomUserDetailsService;
import com.orange.otheatre.service.EventService;
import com.orange.otheatre.otheatre.service.HallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/manageEvents")
public class ManageEventsController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomUserDetailsService userService;

    @Autowired
    HallService hallService;

    @Autowired
    EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public String manageEventsPage(HttpServletRequest request, Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        String email = request.getUserPrincipal().getName();
        User user = (User) userService.loadUserByUsername(email);
        UserRole userRole = user.getRole();

        model.addAttribute("eventToAdd", new Event());
        model.addAttribute("hallsList", hallService.findAll());
        model.addAttribute("error", new Exception("Can't save event! Please try again").getMessage());

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            model.addAttribute("principal", principal);
        }

        if (userRole.equals(UserRole.ADMIN)) {
            model.addAttribute("events", eventService.findAll());
            return "manageEvents";
        }

        if (userRole.equals(UserRole.PLAY_ORGANIZER)) {
            model.addAttribute("events", eventService.findAllByUser(user));
            return "manageEvents";
        }

        return "redirect:/access-denied";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addEvent")
    public String addEventSubmit(HttpServletRequest request,
                                 @ModelAttribute Event eventToAdd,
                                 @RequestParam(value = "eventHall") String hallName) {


        if (hallName != null) {
            List<Hall> halls = new ArrayList<>();
            Hall hall = hallService.findByHallName(hallName);
            if (hall != null) {
                halls.add(hall);
                eventToAdd.setHalls(halls);
            }
        }

        if (request.getUserPrincipal() != null) {
            String email = request.getUserPrincipal().getName();
            User user = (User) userService.loadUserByUsername(email);
            eventToAdd.setEventOwner(user);
        }

        try {
            eventService.saveEvent(eventToAdd, hallName);
            return "redirect:/manageEvents";
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        return "redirect:/manageEvents?addEvent=failed";


    }

}
