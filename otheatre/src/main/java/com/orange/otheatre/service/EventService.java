package com.orange.otheatre.service;

import com.orange.otheatre.entities.Event;
import com.orange.otheatre.repositories.EventRepository;
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
