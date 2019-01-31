package com.numbDev.Sputnik.Controllers;

import com.numbDev.Sputnik.DB.DBService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    private final DBService dbService;

    public BaseController(DBService dbService) {
        this.dbService = dbService;
    }

    @MessageMapping("/test")
    //@SendTo("/topic/Beep")
    public String getTest(String message) {
        dbService.addUser(message);
        return "Hi! " + message;
    }
}