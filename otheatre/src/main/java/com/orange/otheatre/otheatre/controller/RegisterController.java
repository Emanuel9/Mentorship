/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.controller;

import com.orange.otheatre.otheatre.entities.User;
import com.orange.otheatre.otheatre.model.UserRole;
import com.orange.otheatre.otheatre.service.RegisterService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Marius.Herta
 */
@Controller
public class RegisterController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegisterService registerService;

    //    @PreAuthorize("hasAnyRole('PLAY_ORGANIZER','ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String registerForm(HttpServletRequest request ){
        LOGGER.info("Register: Checking if the user is logged in");
        if ( request.getUserPrincipal() != null ) {
            LOGGER.info("Register: User is logged in, redirecting to homepage.");
            return "redirect:/";
        }

        LOGGER.info("Register: User is not logged in, displaying registerPage.");
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String register(HttpServletRequest request, HttpSession session, Model model) throws InterruptedException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(email, password, UserRole.PARTICIPANT);
        registerService.addUser(user);

        try {
            LOGGER.info("Register: Trying to create user {}", user.getEmail());
            request.login(email, password);
        } catch ( Exception ex ) {
            LOGGER.debug("Register: "+ ex.toString() + String.valueOf(ex.getStackTrace()));
        }

        LOGGER.info("Register: User {} was created successfully, redirecting to homepage", user.getEmail());
        return "redirect:/";

    }
}
