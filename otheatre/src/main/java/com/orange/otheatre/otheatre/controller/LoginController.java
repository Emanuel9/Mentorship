package com.orange.otheatre.otheatre.controller;

import com.orange.otheatre.otheatre.entities.User;
import com.orange.otheatre.otheatre.model.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginForm(@RequestParam(required = false) Boolean error, HttpServletRequest request){
        if ( request.getUserPrincipal() != null ) {
            return "redirect:/";
        }

        return "loginPage";
    }

    @RequestMapping(method = RequestMethod.GET, value= "/logout")
    public String logoutForm( HttpServletRequest request ) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if ( session != null ) {
            session.invalidate();
        }

        return "homePage";
    }
}
