package com.rustedshark.mud.services.session;

import com.rustedshark.mud.MudAppContext;
import com.rustedshark.mud.data.game.player.PlayerSession;
import com.rustedshark.mud.injection.RuntimeInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmbeddedSessionService implements SessionService {

    private Map<String, PlayerSession> _activeSessions = new HashMap<>();

    @Autowired
    private RuntimeInjector injector;

    @Autowired
    private ApplicationContext context;

    @Override
    public Optional<PlayerSession> sessionExists(WebSocketSession webSocketSession) {
        if(!_activeSessions.containsKey(webSocketSession.getId())) {
            return Optional.empty();
        } else {
            return Optional.of(_activeSessions.get(webSocketSession.getId()));
        }
    }

    @Override
    public PlayerSession createGameSession(WebSocketSession webSocketSession) {
        //PlayerSession session = context.getAutowireCapableBeanFactory().createBean(PlayerSession.class);
        PlayerSession session = new PlayerSession(null);
        //injector.bind(session);
        //context.getAutowireCapableBeanFactory().autowireBean(session);

        return null;
    }

    @Override
    public List<PlayerSession> activeSessions() {
        return null;
    }

}
