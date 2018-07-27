package com.orange.otheatre.service;

import com.orange.otheatre.entities.User;
import com.orange.otheatre.repositories.UserRepository;
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
