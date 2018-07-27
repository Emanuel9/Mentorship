package com.orange.otheatre.service;

import com.orange.otheatre.entities.Comment;
import com.orange.otheatre.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {

        return commentRepository.saveAndFlush(comment);
    }
}