/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.repositories;

import com.orange.otheatre.entities.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marius.Herta
 */
@Repository
public interface UserRepository extends JpaRepository <User, Integer>, CrudRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}
