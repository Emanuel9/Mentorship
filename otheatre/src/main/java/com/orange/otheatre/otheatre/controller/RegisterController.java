/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.controller;

import com.orange.otheatre.otheatre.entities.User;
import com.orange.otheatre.otheatre.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marius.Herta
 */
@RestController
public class RegisterController {
    
    @Autowired
    private RegisterService registerService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public User addUser(@RequestBody User user){
        return registerService.addUser(user);
    }
}
