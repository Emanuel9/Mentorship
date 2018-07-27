package com.orange.otheatre.controller;


import com.orange.otheatre.entities.User;
import com.orange.otheatre.repositories.EventRepository;
import com.orange.otheatre.repositories.HallRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    HallRepository hallRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String homePage(HttpSession session, Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("halls", hallRepository.findAll());

        if ( SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ) {
            LOGGER.info("Homepage: Checking if user is authenticated.");
            try {
                User principal = (User) authentication.getPrincipal();
                model.addAttribute("principal", principal);
                LOGGER.info("Homepage: Found authenticated user {}", principal.getEmail());
            } catch ( Exception ex ) {
                LOGGER.debug("Homepage: " + ex.toString() + String.valueOf(ex.getStackTrace()));
            }
        }
        LOGGER.info("Homepage: Displaying homePage.");
        return "homePage";
    }
}
