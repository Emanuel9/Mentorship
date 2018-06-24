package com.orange.otheatre.otheatre.repositories;

import com.orange.otheatre.otheatre.entities.Comment;
import com.orange.otheatre.otheatre.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer>, CrudRepository<Comment, Integer> {

}
