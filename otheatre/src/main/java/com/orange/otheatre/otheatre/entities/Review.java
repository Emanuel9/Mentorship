/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.entities;

import com.orange.otheatre.otheatre.model.Mark;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private int id;
    private String writtenReview;
    private Mark userScore;
    
    @ManyToOne
    private Event event;

    @ManyToOne
    private UserProfile reviewCreator;

    public Review() {
    }

    public Review(String writtenReview, Mark userScore, Event event, UserProfile reviewCreator) {
        this.writtenReview = writtenReview;
        this.userScore = userScore;
        this.event = event;
        this.reviewCreator = reviewCreator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWrittenReview() {
        return writtenReview;
    }

    public void setWrittenReview(String writtenReview) {
        this.writtenReview = writtenReview;
    }

    public Mark getUserScore() {
        return userScore;
    }

    public void setUserScore(Mark userScore) {
        this.userScore = userScore;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserProfile getReviewCreator() {
        return reviewCreator;
    }

    public void setReviewCreator(UserProfile reviewCreator) {
        this.reviewCreator = reviewCreator;
    }
    
    
}
