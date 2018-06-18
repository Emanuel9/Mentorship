package com.orange.otheatre.otheatre.service;

import com.orange.otheatre.otheatre.entities.Event;
import com.orange.otheatre.otheatre.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public Event saveEvent(Event event) {

        return eventRepository.saveAndFlush(event);
    }

}
