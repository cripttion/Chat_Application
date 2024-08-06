package com.cripttion.chatapp.service.chatservice;

import com.cripttion.chatapp.Dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, Set<WebSocketSession>> chatSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Extract chatId from the URI query parameter
        String chatId = getChatId(session);
        chatSessions.computeIfAbsent(chatId, k -> ConcurrentHashMap.newKeySet()).add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        MessageDTO msg = objectMapper.readValue(message.getPayload().toString(), MessageDTO.class);
        String chatId = msg.getChatID();

        // Send the message to all sessions associated with the chatId
        Set<WebSocketSession> sessions = chatSessions.get(chatId);
        if (sessions != null) {
            for (WebSocketSession wsSession : sessions) {
                if (wsSession.isOpen()) {
                    wsSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
                }
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String chatId = getChatId(session);
        Set<WebSocketSession> sessions = chatSessions.get(chatId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                chatSessions.remove(chatId);
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private String getChatId(WebSocketSession session) {
        // Extract chatId from session URI query parameter
        return session.getUri().getQuery().split("=")[1]; // Example: ws://server/ws?chatId=12345
    }
}
