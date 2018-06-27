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
    private String userComment;
    
    @ManyToOne
    private Event event;
    
    @ManyToOne
    private UserProfile commentCreator;

    public Comment() {

    }

    public Comment(Integer commentId, String userComment, Event event, UserProfile commentCreator) {
        this.commentId = commentId;
        this.userComment = userComment;
        this.event = event;
        this.commentCreator = commentCreator;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserProfile getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(UserProfile commentCreator) {
        this.commentCreator = commentCreator;
    }
}
