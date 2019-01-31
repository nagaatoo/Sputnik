package com.numbDev.Sputnik.Page;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.numbDev.Sputnik.BackLog.MapAppender;
import com.numbDev.Sputnik.DB.DBService;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.SQLException;

@Theme("valo")
@SpringUI
@EnableScheduling
@Push
public final class MainPage extends UI {

    private MapAppender appender;
    private static Logger logger = (Logger) LoggerFactory.getLogger(MainPage.class);

    private TextArea area = new TextArea();
    private TextArea log = new TextArea();
    private final DBService dbService;
    private StringBuilder logString = new StringBuilder();

    public MainPage(DBService dbService) {
        this.dbService = dbService;
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

    // Dirty hack
    private void makeBeep() {
        String text = area.getValue();
        area.setValue(text + "\n Beeeep!");
    }

    @Scheduled(cron = "*/1 * * * * * ")
    private void logger() throws SQLException {
        logString.setLength(0);
        StringBuilder text = new StringBuilder("Список товарищей, услышавших эхо мощи коммунизма:\n");
        text.append("------------------------------\n\n");
        for (String user : dbService.getBeepUsers()) {
            text.append(user).append("\n");
        }
        this.access(() -> {
            log.setValue(text.toString());
            makeBeep();
        });
    }
}


