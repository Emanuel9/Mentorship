/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.entities;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;

    @Column(name = "user_comment")
    private String userComent;
    
    @ManyToOne
    private Event event;
    
    @ManyToOne
    private UserProfile commentCreator;

    public Comment() {

    }

    public Comment(String userComent, Event event, UserProfile reviewCreator) {
        this.userComent = userComent;
        this.event = event;
        this.commentCreator = reviewCreator;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserComent() {
        return userComent;
    }

    public void setUserComent(String userComent) {
        this.userComent = userComent;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserProfile getReviewCreator() {
        return commentCreator;
    }

    public void setReviewCreator(UserProfile reviewCreator) {
        this.commentCreator = reviewCreator;
    }
    
    
}
