/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.service;


import java.security.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marius Herta <marius.herta@gmail.com>
 */

@Service
public class SecurePassword {
    public String hashPassword(String password){
        String passwordToHash = password;
        String generatedPassword = null;
        
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[]bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i <bytes.length;i++){
                sb.append(Integer.toString((bytes[i] & 0xff)+ 0x100,32).substring(1));
                }
            
            generatedPassword = sb.toString();
            return generatedPassword;
        
        }catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
}
