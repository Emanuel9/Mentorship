package com.orange.otheatre.otheatre.service;

import com.orange.otheatre.otheatre.entities.Event;
import com.orange.otheatre.otheatre.repositories.EventRepository;
import com.orange.otheatre.otheatre.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Service
public class CheckAvailability {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    HallRepository hallRepository;

    public boolean checkAvailability(String hallName, LocalDateTime localDateTime) {
        Predicate<Event> dateMatch = e -> e.getEventStartDate().equals(localDateTime);
        List<Event> eventsInHall = eventRepository.findAllByHalls(hallRepository.findByHallName(hallName));

        if(eventsInHall.stream().anyMatch(dateMatch)){
            return true;
        }

        return false;


    }



}
