package com.orange.otheatre.exceptions;

public class RowException extends Exception {
    public RowException() {
        super("The hall doesn't hold that many rows");
    }
}
