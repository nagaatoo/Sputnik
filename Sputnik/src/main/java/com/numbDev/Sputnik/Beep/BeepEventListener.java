package com.numbDev.Sputnik.Beep;

import org.springframework.context.ApplicationListener;

public class BeepEventListener implements ApplicationListener<BeepEvent> {

    @Override
    public void onApplicationEvent(BeepEvent beepEvent) {
        System.out.println("listener");
    }

}
