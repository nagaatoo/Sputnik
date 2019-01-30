package com.numbDev.Sputnik.Page;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.numbDev.Sputnik.BackLog.MapAppender;
import com.numbDev.Sputnik.Beep.BeepEvent;
import com.numbDev.Sputnik.Beep.BeepEventPublisher;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Theme("valo")
@SpringUI
@EnableScheduling
@Push
public final class MainPage extends UI implements IBeep {

    private MapAppender appender;
    private static Logger logger = (Logger) LoggerFactory.getLogger(MainPage.class);

    private TextArea area = new TextArea();
    private TextArea log = new TextArea();
    private final SimpMessagingTemplate template;
    private final BeepEventPublisher publisher;
    private StringBuilder logString = new StringBuilder();


    public MainPage(SimpMessagingTemplate template, BeepEventPublisher beepEventPublisher) {
        this.template = template;
        this.publisher = beepEventPublisher;
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        appender = (MapAppender) context.getLogger("ROOT").getAppender("map");
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout vlRoot = new VerticalLayout();
        VerticalLayout vlLog = new VerticalLayout();
        HorizontalLayout hzArea = new HorizontalLayout();
        vlLog.setMargin(false);
        vlLog.setSpacing(false);
        vlLog.setSizeFull();
        log.setSizeFull();
        vlLog.addComponents(log);

        hzArea.setWidth("100%");
        hzArea.addComponents(area);

        vlRoot.setSizeFull();
        vlRoot.setMargin(true);

        vlRoot.addComponents(hzArea, vlLog);
        vlRoot.setExpandRatio(vlLog, 0.8f);
        this.setContent(vlRoot);
    }

    public void makeBeep() {
        String text = area.getValue();
        area.setValue(text + "\n beep!");
    }

//    @Scheduled(cron = "*/5 * * * * *")
//    private void gPoint() {
//        System.out.println(message);
//        this.access(() -> area.setValue(area.getValue() + message + "\n" ));
//        try {
//            template.convertAndSend("/topic/Beep", message);
//        } catch (MessagingException e) {
//            logger.error(e.getMessage());
//        }
//    }

    @Scheduled(cron = "*/1 * * * * * ")
    private void logger() {
        logString.setLength(0);
        appender.getEventMap().forEach(l -> logString.append("\n").append(l));
        this.access(() -> log.setValue(logString.toString()));
       // publisher.publishEvent("yolo");
    }

    // заставить слушать Beeep
  //  @EventListener
    public void beepListerenPub(final BeepEvent event) {
        System.out.println("123");
    }
}


