package com.orange.otheatre.otheatre;

import com.orange.otheatre.otheatre.entities.Event;
import com.orange.otheatre.otheatre.entities.Hall;
import com.orange.otheatre.otheatre.entities.Seat;
import com.orange.otheatre.otheatre.repositories.EventRepository;
import com.orange.otheatre.otheatre.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.chrono.JapaneseEra.values;

@Component
public class TestHelper {

    public static final String TEST_EVENT_NAME = "TestEvent1";
    public static final String TEST_EVENT_DESCRIPTION ="TestEvent description";

    private static long minStart = 1L;
    private static long maxStart = 48L;
    private static long generatedStart = minStart + (long)(Math.random() *(maxStart-minStart));
    public static final LocalDateTime TEST_EVENT_START_DATE = LocalDateTime.now().plusHours(generatedStart);

    private static long min = 60L;
    private static long max = 120L;
    private static long generatedEnd = min + (long)(Math.random() *(max-min));
    public static final LocalDateTime TEST_EVENT_END_DATE = TEST_EVENT_START_DATE.plusMinutes(generatedEnd);


    private static final String HALL_NAME = "Haret";
    private static final Integer NUMBER_OF_ROWS_FOR_HALL = 10;
    private static final Integer NUMBER_OF_COLS_FOR_HALL = 10;


    @Autowired
    HallRepository hallRepository;

    @Autowired
    EventRepository eventRepository;

    private final List<Hall> TEST_EVENT_HALL = new ArrayList<>();

    public Event event;

    public Hall hall;

    public Event createEvent() {
        event = new Event();
        event.setEventTitle(TEST_EVENT_NAME);
        event.setEventDescription(TEST_EVENT_DESCRIPTION);
        event.setEventStartDate(TEST_EVENT_START_DATE);
        event.setEventEndDate(TEST_EVENT_END_DATE);
        if ( hall == null ) {
            createHall();
        }

        if ( TEST_EVENT_HALL.isEmpty()) {
            TEST_EVENT_HALL.add(hall);
        }
        event.setHalls(TEST_EVENT_HALL);
        return eventRepository.saveAndFlush(event);
    }

    public Event createOrReturnExistingEvent() {
        if(event == null) {
            event = new Event();
            event.setEventTitle(TEST_EVENT_NAME);
            event.setEventDescription(TEST_EVENT_DESCRIPTION);
            event.setEventStartDate(TEST_EVENT_START_DATE);
            event.setEventEndDate(TEST_EVENT_END_DATE);
            if ( hall == null ) {
                createHall();
            }

            if ( TEST_EVENT_HALL.isEmpty()) {
                TEST_EVENT_HALL.add(hall);
            }
            event.setHalls(TEST_EVENT_HALL);
            event = eventRepository.saveAndFlush(event);
        }


        return event;
    }

    public Hall createHall() {
        if ( hall == null ) {
            hall = new Hall();
            hall.setHallName(HALL_NAME);
            hall.setNumberOfRows(NUMBER_OF_ROWS_FOR_HALL);
            hall.setNumberOfSeatsPerRow(NUMBER_OF_COLS_FOR_HALL);
            hall = hallRepository.saveAndFlush(hall);
        }
        return hall;
    }

    @PostConstruct
    public void cleanDataBase() {
        event = null;
        hall = null;
        eventRepository.deleteAll();
        hallRepository.deleteAll();
    }

}
