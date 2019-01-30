package com.numbDev.SputnikClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class SputnikClientApplication {

	public	static void main(String[] args) throws ExecutionException, InterruptedException {
		ApplicationContext context = SpringApplication.run(SputnikClientApplication.class, args);

		WebSocketClient webSocketClient = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
		stompClient.setMessageConverter(new StringMessageConverter());
		String url = "wss://sputnik.cfapps.io:4443/portfolio";
		StompSessionHandler sessionHandler = new SessionHandler();
		StompSession session = stompClient.connect(url, sessionHandler).get();
	}
}

