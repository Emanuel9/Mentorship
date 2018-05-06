/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.service;

import com.orange.otheatre.otheatre.entities.User;
import com.orange.otheatre.otheatre.model.UserRole;
import com.orange.otheatre.otheatre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marius.Herta
 */
@Service
public class RegisterService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User addUser(User user){
        if(user.getRole()==null){
            user.setRole(UserRole.PARTICIPANT);
        }
        return userRepository.save(user);
    }
    
}
