package com.numbDev.Sputnik.Controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @MessageMapping("/test")
    // Заменить на simple....template
    @SendTo("/topic/Beep")
    public String getTest(String message) {
        System.out.println("yep");
        return "Hi! " + message;
    }
}