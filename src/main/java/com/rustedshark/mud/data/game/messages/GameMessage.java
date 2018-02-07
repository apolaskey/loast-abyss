package com.rustedshark.mud.data.game.messages;

import org.springframework.web.socket.TextMessage;

public interface GameMessage {

    String getMessage();

    default String eventType() {
        return this.getClass().getSimpleName();
    }

    default String toJson() {
        return "";
    }

    default TextMessage toClientMessage() {
        return new TextMessage(toJson());
    }

}
