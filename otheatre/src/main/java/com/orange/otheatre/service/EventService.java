package com.orange.otheatre.service;

import com.orange.otheatre.entities.Event;
import com.orange.otheatre.entities.User;
import com.orange.otheatre.entities.UserProfile;
import com.orange.otheatre.repositories.EventRepository;
import com.orange.otheatre.repositories.HallRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EventService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EventRepository eventRepository;

    @Autowired
    HallRepository hallRepository;

    private Boolean checkRequirementsToAddANewEvent(Event newEvent, String hallName) {

        Predicate<Event> firstCheck = e -> (newEvent.getEventStartDate().isAfter(e.getEventStartDate()) && newEvent.getEventEndDate().isBefore(e.getEventEndDate()));
        Predicate<Event> secondCheck = e -> (newEvent.getEventEndDate().isAfter(e.getEventStartDate()) && newEvent.getEventEndDate().isBefore(e.getEventEndDate()));
        Predicate<Event> thirdCheck = e -> (newEvent.getEventStartDate().isAfter(e.getEventEndDate()) && newEvent.getEventStartDate().isBefore(e.getEventEndDate().plusMinutes(15)));
        Predicate<Event> fourthCheck = e -> (newEvent.getEventEndDate().isAfter(e.getEventStartDate().minusMinutes(15)) && newEvent.getEventEndDate().isBefore(e.getEventStartDate()));


        List<Event> eventsInHall = eventRepository.findAllByHalls(hallRepository.findByHallName(hallName));

        if (eventsInHall.stream().anyMatch(firstCheck)) {
            LOGGER.error("Add event error: the event failed the firstCheck!");
            return true;
        } else if (eventsInHall.stream().anyMatch(secondCheck)) {
            LOGGER.error("Add event error: the event failed the secondCheck!");
            return true;
        } else if (eventsInHall.stream().anyMatch(thirdCheck)) {
            LOGGER.error("Add event error: the event failed the thirdCheck!");
            return true;
        } else if (eventsInHall.stream().anyMatch(fourthCheck)) {
            LOGGER.error("Add event error: the event failed the fourthCheck!");
            return true;
        } else return false;
    }

    public Event saveEvent(Event newEvent, String hallName) throws Exception {

        if (checkRequirementsToAddANewEvent(newEvent, hallName)) {
            throw new Exception("Can't add event! Please check the requirements and try again.") ;

        } else eventRepository.saveAndFlush(newEvent);

        return newEvent;
    }

    public boolean isEligibileForReview(Optional<UserProfile> optionalUserProfile, Event event) {

        LOGGER.info("Checking if the user attended the event");

        if(optionalUserProfile.isPresent()){
            UserProfile userProfile = optionalUserProfile.get();
            LOGGER.info("EventPage: Found authenticated user: "+ userProfile.getFirstName());

            for(Event e : userProfile.getEventsAttended()){
                if(e.getEventTitle().equals(event.getEventTitle())){
                    LOGGER.info("The user attended the event.");
                    return true;
                }
            }
        }

        LOGGER.info("The user didn't attend the event.");
        return false;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findAllByUser(User user) {
        return eventRepository.findAllByEventOwner(user);
    }

}
