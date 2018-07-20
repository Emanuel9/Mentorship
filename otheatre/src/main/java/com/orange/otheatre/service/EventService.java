package com.orange.otheatre.service;

import com.orange.otheatre.entities.Event;
import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public Event saveEvent(Event event) {
        return eventRepository.saveAndFlush(event);
    }

    public boolean isEligibileForReview(UserProfile userProfile, Event event) {

        LOGGER.info("Checking if event is over.");
        if(event.getEventEndDate().isAfter(LocalDateTime.now())){
            LOGGER.info("The event is not over yet!");
            return false;
        }
        LOGGER.info("The event is over.");


        LOGGER.info("Checking if the user attended the event");
        if(!userProfile.getEventsAttended().contains(event)){
            LOGGER.info("The user didn't attend the event.");
            return false;
        }
        LOGGER.info("The user attended the event.");

        return true;

    }
}

