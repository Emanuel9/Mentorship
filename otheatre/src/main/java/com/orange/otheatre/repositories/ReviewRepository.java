package com.orange.otheatre.repositories;


import com.orange.otheatre.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>, CrudRepository<Review, Integer> {
}
