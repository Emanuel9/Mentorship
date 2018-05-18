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

    @Autowired
    private RegisterService registerService;

    //    @PreAuthorize("hasAnyRole('PLAY_ORGANIZER','ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String registerForm(){
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String register(HttpServletRequest request, Model model) throws InterruptedException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(email, password, UserRole.PARTICIPANT);
        registerService.addUser(user);

        Thread.sleep(5000);
        return "redirect:/";

    }
}
