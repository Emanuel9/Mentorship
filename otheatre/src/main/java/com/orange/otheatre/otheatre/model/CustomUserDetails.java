/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.model;

import com.orange.otheatre.otheatre.entities.User;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Marius Herta <marius.herta@gmail.com>
 */
public class CustomUserDetails extends User implements UserDetails{

    public CustomUserDetails(User user) {
        super(user);
    }
    
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> rolesList = 
                Arrays.stream(UserRole.values())
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());        
        return rolesList;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
         return true;
    }

    @Override
    public boolean isAccountNonLocked() {
         return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
         return true;
    }

    @Override
    public boolean isEnabled() {
         return true;
    }

        
}
