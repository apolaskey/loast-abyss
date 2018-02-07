package com.rustedshark.mud.injection.components;

import com.rustedshark.mud.injection.services.login.LoginService;
import com.rustedshark.mud.injection.services.session.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.inject.Inject;

/**
 * Our defacto "Main" for when a new socket is opened and players begin connecting
 */
@Component
public class GameWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(GameWebSocketHandler.class);

    private final SessionService sessionService;
    private final LoginService loginService;

    @Inject public GameWebSocketHandler(
            SessionService gameSessionService,
            LoginService loginService
    ) {
        this.sessionService = gameSessionService;
        this.loginService = loginService;
    }

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        logger.info("Incoming Connection: {} from [ {} | {} ]",
                session.getId(), session.getRemoteAddress(), session.getLocalAddress());
        sessionService.createGameSession(session);
        session.sendMessage(new TextMessage("Welcome, please input your account name:"));
    }

    @Override
    protected void handleTextMessage(final WebSocketSession socket, final TextMessage message) {
        logger.info("[{} | {}] - {} sent:\r\n{}",
                socket.getRemoteAddress(), socket.getLocalAddress(),
                socket.getId(), message.getPayload());
        sessionService.activeSessions().get(socket.getId()).handleCommand(message.getPayload());
    }

    @Override
    public void afterConnectionClosed(final WebSocketSession socket, final CloseStatus status) {
        // Cleanup player session
        logger.info("Active Players Session: {}", sessionService.activeSessions().size());
        //sessionService.activeSessions().get(socket.getId()).saveAndClose();
    }

    @Override
    public void handleTransportError(final WebSocketSession socket, final Throwable exception) {
        // Critical Error detection
    }
}
