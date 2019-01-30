package com.numbDev.Sputnik.Controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public final class WebSockHandler extends TextWebSocketHandler {
// переделать под extends WebSocketMessageBrokerConfigurer
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public String greeting(String message) throws Exception {
//        return "Test";
//    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("----> " + message);
    }
}
