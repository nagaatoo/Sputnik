package com.numbDev.Sputnik.Beep;

import org.springframework.context.ApplicationEvent;

public class BeepEvent extends ApplicationEvent {

    private final String message;

    public BeepEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}