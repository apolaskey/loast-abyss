package com.rustedshark.mud.data.game;

import com.rustedshark.mud.commands.AbstractCommand;
import com.rustedshark.mud.commands.CommandManager;
import com.rustedshark.mud.data.game.messages.GameMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.socket.WebSocketSession;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;

/**
 * Main session object for any given connection
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GameSession {

    private static final Logger logger = LoggerFactory.getLogger(GameSession.class);

    @Inject
    private CommandManager commandManager;

    private final WebSocketSession socketSession;

    public GameSession(WebSocketSession socketSession) {
        this.socketSession = socketSession;
    }

    public GameSession handleInput(String input) throws IOException {
        Optional<AbstractCommand> optionalCommand = commandManager.createBuilder(input).buildCommand();
        if(optionalCommand.isPresent()) {
            optionalCommand.get().execute();
        } else {
            sendMessage(() -> "Huh? You can't do that.");
        }
        return this;
    }

    public String getId() {
        return socketSession.getId();
    }

    /**
     * Sends a message to the client
     * @param message {@link GameMessage} Structured Message
     * @return {@link Optional} of {@link GameSession} | Empty if the message failed to send
     */
    public boolean sendMessage(GameMessage message) throws IOException {
        socketSession.sendMessage(message.toClientMessage());
        return true;
    }

    public GameSession close() {
        try {
            socketSession.close();
        } catch (IOException e) {
            logger.error("Game Session[sid=\"{}\"]: Failed to terminate cleanly (already closed?)", e);
        }
        return this;
    }
}
