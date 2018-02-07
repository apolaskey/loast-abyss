package com.rustedshark.mud.injection.services.session;

import com.rustedshark.mud.data.game.GameSession;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;

public interface SessionService {
    GameSession createGameSession(WebSocketSession socket);
    Optional<GameSession> getGameSession(WebSocketSession socket);
}
