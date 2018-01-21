package com.rustedshark.mud.services.session;

import com.rustedshark.mud.models.player.PlayerSession;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    Optional<PlayerSession> sessionExists(WebSocketSession webSocketSession);
    PlayerSession createGameSession(WebSocketSession webSocketSession);
    List<PlayerSession> activeSessions();
}
