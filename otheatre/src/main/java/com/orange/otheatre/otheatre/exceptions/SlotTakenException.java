package com.orange.otheatre.otheatre.exceptions;

import org.springframework.validation.BindingResult;

public class SlotTakenException extends Exception {

    public SlotTakenException() {
        super("The chosen start date for your event is taken for the desired hall!");
    }

}
