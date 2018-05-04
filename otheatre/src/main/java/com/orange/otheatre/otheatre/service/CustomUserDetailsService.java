/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.service;

import com.orange.otheatre.otheatre.entities.User;
import com.orange.otheatre.otheatre.model.CustomUserDetails;
import com.orange.otheatre.otheatre.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marius Herta <marius.herta@gmail.com>
 */

@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        
        optionalUser
                .orElseThrow(()-> new UsernameNotFoundException("Username was not found!"));
        return optionalUser
                .map(CustomUserDetails::new)
                .get();
    }
    
}
