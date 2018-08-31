package com.orange.otheatre.service;

import com.orange.otheatre.entities.Comment;
import com.orange.otheatre.entities.User;
import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.repositories.CommentRepository;
import com.orange.otheatre.repositories.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public Comment saveComment(Comment comment) {

        LOGGER.info("CommentService: Comment added");
        return commentRepository.saveAndFlush(comment);

    }



}
