package com.rustedshark.mud.session;

import com.rustedshark.mud.data.game.GameSession;
import com.rustedshark.mud.injection.RuntimeInjector;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.inject.Inject;

@Component
public class GameSessionBuilder {

    @Inject
    private RuntimeInjector injector;

    public GameSession build(WebSocketSession socketSession) {
        GameSession session = new GameSession(socketSession);
        return injector.bind(session);
    }

}
