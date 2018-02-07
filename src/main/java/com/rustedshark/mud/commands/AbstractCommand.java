package com.rustedshark.mud.commands;

import com.rustedshark.mud.data.game.GameSession;
import com.rustedshark.mud.data.game.messages.GameMessage;
import com.rustedshark.mud.injection.components.WebSocketSessions;
import com.rustedshark.mud.injection.services.session.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.inject.Inject;
import java.io.IOException;

public abstract class AbstractCommand {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCommand.class);

    private final GameSession session;

    public AbstractCommand(final GameSession session) {
        this.session = session;
    }

    public abstract void execute();

    /**
     * Returns the active game session for the client
     * @return {@link GameSession}
     */
    public GameSession getSession() {
        return session;
    }

}
