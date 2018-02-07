package com.rustedshark.mud.injection.services.session;

import com.rustedshark.mud.data.game.GameSession;
import com.rustedshark.mud.data.game.messages.NoticeMessage;
import com.rustedshark.mud.injection.components.WebSocketSessions;
import com.rustedshark.mud.session.GameSessionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmbeddedSessionService implements SessionService {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedSessionService.class);
    private static final Map<String, GameSession> _activeSessions = new ConcurrentHashMap<>();

    @Inject
    private GameSessionBuilder gameSessionBuilder;

    @Override
    public GameSession createGameSession(WebSocketSession webSocketSession) {
        if(_activeSessions.containsKey(webSocketSession.getId())) {
            return _activeSessions.get(webSocketSession.getId());
        } else {
            return _activeSessions.put(webSocketSession.getId(), gameSessionBuilder.build(webSocketSession));
        }
    }

    /**
     * Returns the active session found by key
     * @param webSocketSession {@link WebSocketSession}
     * @return {@link GameSession}
     */
    public Optional<GameSession> getGameSession(WebSocketSession webSocketSession) {
        if(_activeSessions.containsKey(webSocketSession.getId())) {
            return Optional.of(_activeSessions.get(webSocketSession.getId()));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Current active game sessions active
     * @return {@link Integer}
     */
    public int activeSessionCount() {
        return _activeSessions.size();
    }

    /**
     * Sends the given message to all connected clients
     * @return {@link WebSocketSession}
     */
    public EmbeddedSessionService sendNotice(String message) {
        final List<String> invalidSessions = Collections.synchronizedList(new ArrayList<>());
        _activeSessions.values().parallelStream().forEach(session -> {
            try {
                session.sendMessage(new NoticeMessage(message));
            } catch (IOException e) {
                invalidSessions.add(session.getId());
            }
        });
        invalidSessions.forEach(this::closeSession);
        return this;
    }

    public EmbeddedSessionService closeSession(String id) {
        if(_activeSessions.containsKey(id)) {
            _activeSessions.get(id).close();
            _activeSessions.remove(id);
        } else {
            logger.warn("Session Service[sid=\"{}\"]: Failed to locate session to shutdown (Session terminated, or incorrect id)");
        }
        return this;
    }
}
