package com.orange.otheatre.repositories;

import com.orange.otheatre.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer>, CrudRepository<Hall, Integer> {

    Hall findByHallName(String hallName);
}
