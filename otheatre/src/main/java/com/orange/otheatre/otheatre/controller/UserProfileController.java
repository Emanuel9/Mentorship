package com.orange.otheatre.otheatre.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserProfileController {

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public String profilePage( HttpServletRequest request, Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if ( authentication != null ) {
            Object principal = authentication.getPrincipal();
            model.addAttribute("principal", principal);
        }

        return "profile";
    }

}
