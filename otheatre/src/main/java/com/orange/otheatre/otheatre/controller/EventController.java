package com.orange.otheatre.otheatre.controller;

import com.orange.otheatre.otheatre.entities.Comment;
import com.orange.otheatre.otheatre.entities.Event;
import com.orange.otheatre.otheatre.entities.Review;
import com.orange.otheatre.otheatre.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class EventController {

    @Autowired
    EventRepository eventRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET,value = "/event/{eventId}")
    public String viewEvent (@PathVariable(value="eventId") String eventId,
                             Model model){
        LOGGER.info("Event: Welocome to EventController. I am now going to show information about event id " + eventId);
        LOGGER.info("Event: Checking if event id is null.");
        Event event;
        List<Event> eventList;
        List<Review> reviewList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        if(eventId != null){
            LOGGER.info("Event: Event id is not null. Searching event in database.");
            Optional<Event> optionalEvent = eventRepository.findById(Long.parseLong(eventId));
            event = optionalEvent.get();
            model.addAttribute("event",event);
            LOGGER.info("Event: Event entitled {} was put in model.", event.getEventTitle());

            LOGGER.info("Event: Searching for all events entitled: " + event.getEventTitle());
            eventList = eventRepository.findAllByEventTitle(event.getEventTitle());
            LOGGER.info("Event: Found {} events.", eventList.size());

            LOGGER.info("Event: Searching for reviews and comments from all events entitled: " + event.getEventTitle());
            for(Event e : eventList){
                reviewList.addAll(e.getEventReviews());
                commentList.addAll(e.getEventComments());
            }

            LOGGER.info("Event: Found {} reviews and {} comments ", reviewList.size(), commentList.size());

            model.addAttribute("reviews", reviewList);
            model.addAttribute("comments", commentList);

            LOGGER.info("Event: Added reviews and comments to model.");

        }
        return "event";
    }
}
