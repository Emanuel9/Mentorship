/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Marius Herta <marius.herta@gmail.com>
 */
@Entity
@Table(name = "hall")
public class Hall implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hall_id")
    private Integer hallId;

    @Column(unique = true)
    private String hallName;
    
    @OneToMany(mappedBy = "hall",cascade = CascadeType.ALL)
    private List<Seat>seats;

    @ManyToMany(mappedBy = "halls")
    private List<Event> events;

    private Integer numberOfRows;

    private Integer numberOfSeatsPerRow;

    public Hall() {
    }

    public Hall(List<Seat> seats) {
        this.seats = seats;
    }

    public Hall(Integer hallId, String hallName, List<Seat> seats, List<Event> events, Integer numberOfRows, Integer numberOfSeatsPerRow) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.seats = seats;
        this.events = events;
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
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

    @Override
    public String toString() {
        return  hallName;
    }
}
