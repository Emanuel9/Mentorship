/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.entities;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Marius Herta <marius.herta@gmail.com>
 */
@Entity
@Table(name = "hall")
public class Hall {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hallId;

    @Column(unique = true)
    private String hallName;
    
    @OneToMany(mappedBy = "hall",cascade = CascadeType.ALL)
    private List<Seat>seats;

    @OneToOne
    private Event event;

    private Integer numberOfRows;

    private Integer numberOfSeatsPerRow;

    public Hall() {
    }

    public Hall(List<Seat> seats) {
        this.seats = seats;
    }

    public Hall(String hallName, List<Seat> seats, Event event, Integer numberOfRows, Integer numberOfSeatsPerRow) {
        this.hallName = hallName;
        this.seats = seats;
        this.event = event;
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public Integer getNumberOfSeatsPerRow() {
        return numberOfSeatsPerRow;
    }

    public void setNumberOfSeatsPerRow(Integer numberOfSeatsPerRow) {
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
    }
}
