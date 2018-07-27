package com.orange.otheatre.service;

import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    public UserProfile saveUserProfile(UserProfile userProfile){
        return userProfileRepository.saveAndFlush(userProfile);
    }
}
