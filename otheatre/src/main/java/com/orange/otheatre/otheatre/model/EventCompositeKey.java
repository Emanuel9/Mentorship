/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Marius Herta <marius.herta@gmail.com>
 */
public class EventCompositeKey implements Serializable{
    
    private String eventTitle;
    
    private LocalDateTime eventDate;

    public EventCompositeKey() {
    }

    public EventCompositeKey(String eventTitle, LocalDateTime eventDate) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
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
}
