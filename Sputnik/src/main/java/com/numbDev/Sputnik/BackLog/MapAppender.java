package com.numbDev.Sputnik.BackLog;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class MapAppender extends AppenderBase<ILoggingEvent> {

    private List<ILoggingEvent> eventMap = new ArrayList<>();

    public List<ILoggingEvent> getEventMap() {
        return eventMap;
    }

    @Override
    protected void append(ILoggingEvent event) {
        System.out.println("-> " + event);
        eventMap.add(event);
    }
}
