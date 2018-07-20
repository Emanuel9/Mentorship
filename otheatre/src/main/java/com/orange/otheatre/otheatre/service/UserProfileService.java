package com.orange.otheatre.otheatre.service;

import com.orange.otheatre.otheatre.entities.UserProfile;
import com.orange.otheatre.otheatre.repositories.UserProfileRepository;
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
