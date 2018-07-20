package com.orange.otheatre.otheatre.repositories;

import com.orange.otheatre.otheatre.entities.Comment;
import com.orange.otheatre.otheatre.entities.Event;
import com.orange.otheatre.otheatre.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CrudRepository<Comment, Long> {

}
