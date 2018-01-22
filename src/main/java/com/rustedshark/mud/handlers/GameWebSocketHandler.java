package com.rustedshark.mud.handlers;

import com.rustedshark.mud.data.game.player.PlayerSession;
import com.rustedshark.mud.handlers.flows.CommandHandler;
import com.rustedshark.mud.injection.RuntimeInjector;
import com.rustedshark.mud.services.session.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Our defacto "Main" for when a new socket is opened and players begin connecting
 */
@Component
public class GameWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(GameWebSocketHandler.class);

    private final SessionService _sessionService;

    @Autowired public GameWebSocketHandler(
            SessionService gameSessionService
    ) {
        _sessionService = gameSessionService;
    }

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        logger.info("Incoming Connection: {} from [ {} | {} ]",
                session.getId(), session.getRemoteAddress(), session.getLocalAddress());
        _sessionService.createGameSession(session);
    }

    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) {
        logger.info("[{} | {}] - {} sent:\r\n{}", session.getRemoteAddress(), session.getLocalAddress(), session.getId(), message.getPayload());
        // State switches and command handling
        PlayerSession playerSession = _sessionService.sessionExists(session).orElseGet(() -> _sessionService.createGameSession(session));
    }

    @Override
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) {
        // Cleanup player session
        //logger.info("Active Players Session: {}", _sessionService.getActiveSessions.size());
    }

    @Override
    public void handleTransportError(final WebSocketSession session, final Throwable exception) {
        // Critical Error detection
    }
}
