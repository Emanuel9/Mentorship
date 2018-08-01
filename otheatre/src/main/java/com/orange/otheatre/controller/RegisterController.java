/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.controller;

import com.orange.otheatre.entities.User;
import com.orange.otheatre.model.UserRole;
import com.orange.otheatre.service.RegisterService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.orange.otheatre.service.SecurePassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SecurePassword securePassword;

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
    public String register(HttpServletRequest request) throws InterruptedException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(email, password, UserRole.PARTICIPANT);

        LOGGER.info("Register: Trying to create user {}", user.getEmail());
        try {
            user = registerService.addUser(user);

        } catch (Exception ex){
            LOGGER.error("Register - add user: "+ ex.getMessage());
            throw ex;
        }


        try {
            LOGGER.info("Register: Trying to log in user {}.", user.getEmail());
            request.login(user.getEmail(), password);
        } catch ( Exception ex ) {
            LOGGER.error("Register - login: "+ ex.getMessage());
        }

        LOGGER.info("Register: User {} was successfully created and logged in, redirecting to homepage", user.getEmail());
        return "redirect:/";

    }
}
