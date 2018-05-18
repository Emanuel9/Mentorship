/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.otheatre.otheatre.entities;

import com.orange.otheatre.otheatre.exceptions.RowException;
import com.orange.otheatre.otheatre.exceptions.SeatException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marius Herta <marius.herta@gmail.com>
 */

@Entity
@Table(name = "seat")
public class Seat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer seatId;
    
    private Integer rowNumber;
    
    private Integer seatNumber;
    
    @ManyToOne
    private Hall hall;

    public Seat() {
    }

    public Seat(Integer rowNumber, Integer seatNumber, Hall hall) throws RowException, SeatException {
        this.hall = hall;

        if(this.hall.getNumberOfRows() < rowNumber){
            throw new RowException();
        } else{
            this.rowNumber = rowNumber;
        }

        if(this.hall.getNumberOfSeatsPerRow() < seatNumber){
            throw new SeatException();
        }else{
            this.seatNumber = seatNumber;
        }


    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }  
}
