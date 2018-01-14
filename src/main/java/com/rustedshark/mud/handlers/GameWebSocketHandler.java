package com.rustedshark.mud.handlers;

import com.rustedshark.mud.components.GameSessionService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Our defacto "Main" for when a new socket is opened and players begin connecting
 */
@Component
public class GameWebSocketHandler extends TextWebSocketHandler {

    @Autowired(required = false)
    private Logger logger;

    private final GameSessionService gameSessions;

    @Autowired public GameWebSocketHandler(
            GameSessionService gameSessionService
    ) {
        gameSessions = gameSessionService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        logger.info("New Session Incoming: {}", gameSessions.hashCode());
        gameSessions._sessions.add(session);
        session.sendMessage(new TextMessage("Please input username:"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("[{} | {}] - {} sent:\r\n{}", session.getRemoteAddress(), session.getLocalAddress(), session.getId(), message.getPayload());
        // State switches and command handling
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Cleanup player session
        gameSessions._sessions.remove(session);
        logger.info("Active Players Session: {}", gameSessions._sessions.size());
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Critical Error detection
        super.handleTransportError(session, exception);
    }
}
