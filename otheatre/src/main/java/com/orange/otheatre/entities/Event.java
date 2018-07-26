package com.orange.otheatre.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_title", nullable = false)
    private String eventTitle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User eventOwner;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "event_start_date", nullable = false)
    private LocalDateTime eventStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "event_end_date", nullable = false)
    private LocalDateTime eventEndDate;

    @Column(name = "event_description", nullable = false)
    private String eventDescription;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Review> eventReviews;
    
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Comment> eventComments;
    
    @ManyToMany
    private List<UserProfile> participants;

    @ManyToMany ( cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "event_hall",
    joinColumns = @JoinColumn( name = "event_id"),
    inverseJoinColumns = @JoinColumn ( name = "hall_id"))
    private List<Hall> halls;

    public Event() {
    }

    public Event(Long eventId, String eventTitle, User eventOwner, LocalDateTime eventStartDate, LocalDateTime eventEndDate, String eventDescription, List<Review> eventReviews, List<Comment> eventComments, List<UserProfile> participants, List<Hall> halls) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventOwner = eventOwner;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventDescription = eventDescription;
        this.eventReviews = eventReviews;
        this.eventComments = eventComments;
        this.participants = participants;
        this.halls = halls;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public User getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(User eventOwner) {
        this.eventOwner = eventOwner;
    }

    public LocalDateTime getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDateTime eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public LocalDateTime getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDateTime eventEndDate) {
        this.eventEndDate = eventEndDate;
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }
}