package com.orange.otheatre.otheatre.controller;

import com.orange.otheatre.otheatre.entities.User;
import com.orange.otheatre.otheatre.model.UserRole;
import com.orange.otheatre.otheatre.repositories.EventRepository;
import com.orange.otheatre.otheatre.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ManageEventsController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/manageEvents")
    public String manageEventsPage(HttpServletRequest request, Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        String email = request.getUserPrincipal().getName();
        Optional<User> user = userRepository.findByEmail(email);
        UserRole userRole = user.get().getRole();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            model.addAttribute("principal", principal);
        }

        if (userRole.equals(UserRole.ADMIN)||userRole.equals(UserRole.PLAY_ORGANIZER)) {
            model.addAttribute("events", eventRepository.findAll());
            return "manageEvents";
        }

        return "redirect:/access-denied";
    }

}
