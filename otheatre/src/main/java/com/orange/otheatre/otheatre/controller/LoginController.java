package com.orange.otheatre.otheatre.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginForm(@RequestParam(required = false) Boolean error, HttpServletRequest request){
        LOGGER.info("Login: Checking if the user is logged in");
        if ( request.getUserPrincipal() != null ) {
            LOGGER.info("Login: The user is already logged in, redirecting to homepage");
            return "redirect:/";
        }
        LOGGER.info("Login: The user is not logged in.");
        return "loginPage";
    }

    @RequestMapping(method = RequestMethod.GET, value= "/logout")
    public String logoutForm( HttpServletRequest request ) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        LOGGER.info("Logout: Checking if the user is logged in");
        if ( session != null ) {
            LOGGER.info("Logout: Loging out user.");
            session.invalidate();
        }

        LOGGER.info("Logout: Returning to homepage.");
        return "homePage";
    }
}
