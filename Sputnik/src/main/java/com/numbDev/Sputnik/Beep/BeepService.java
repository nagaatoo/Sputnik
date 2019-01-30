package com.numbDev.Sputnik.Beep;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BeepService {

    private final BeepEventPublisher publisher;
    private final SimpMessagingTemplate template;
    private final String message = "Beeeep!";

    public BeepService(SimpMessagingTemplate template, BeepEventPublisher beepEventPublisher) {
        this.publisher = beepEventPublisher;
        this.template = template;
    }

    @Scheduled(cron = "*/5 * * * * * ")
    private void logger() {
        publisher.publishEvent(message);
        template.convertAndSend("/topic/Beep", message);
    }

    @EventListener
    private void foo(final BeepEvent event) {
        System.out.println(event.getMessage());
    }
}
