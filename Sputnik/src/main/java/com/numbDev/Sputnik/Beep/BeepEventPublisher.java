package com.numbDev.Sputnik.Beep;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class BeepEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public BeepEventPublisher(ApplicationEventPublisher publisher) {
        this.applicationEventPublisher = publisher;
    }

    public void publishEvent(final String message) {
        System.out.println("Publishing custom event. ");
        final BeepEvent customSpringEvent = new BeepEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
