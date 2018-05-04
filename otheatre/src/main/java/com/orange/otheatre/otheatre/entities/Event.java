package com.orange.otheatre.otheatre.entities;

import com.orange.otheatre.otheatre.model.EventCompositeKey;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "event")
@IdClass(EventCompositeKey.class)
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String eventTitle;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDateTime eventDate;
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Review> eventReviews;
    
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Comment> eventComments;
    
    @ManyToMany
    private List<UserProfile> participants;

    @OneToOne
    private Hall hall;

    public Event() {
    }

    public Event(String eventTitle, LocalDateTime eventDate, List<Review> eventReviews, List<Comment> eventComments, List<UserProfile> participants) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventReviews = eventReviews;
        this.eventComments = eventComments;
        this.participants = participants;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public List<Review> getEventReviews() {
        return eventReviews;
    }

    public void setEventReviews(List<Review> eventReviews) {
        this.eventReviews = eventReviews;
    }

    public List<Comment> getEventComments() {
        return eventComments;
    }

    public void setEventComments(List<Comment> eventComments) {
        this.eventComments = eventComments;
    }

    public List<UserProfile> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserProfile> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventTitle, event.eventTitle) &&
                Objects.equals(eventDate, event.eventDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(eventTitle, eventDate);
    }
}