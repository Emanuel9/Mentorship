package com.orange.otheatre.exceptions;

public class SeatException extends Exception {

    public SeatException() {
        super("The hall doesn't hold that many seats per rows");
    }
}
