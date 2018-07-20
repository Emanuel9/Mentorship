package com.orange.otheatre.otheatre.service;

import com.orange.otheatre.otheatre.entities.User;
import com.orange.otheatre.otheatre.model.UserRole;
import com.orange.otheatre.otheatre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.saveAndFlush(user);
    }
}
