package com.rustedshark.mud.data.game.messages;

public class NoticeMessage implements GameMessage {

    private final String message;

    public NoticeMessage(String message) {
        this.message = "Notice: " + message;
    }

    public String getMessage() {
        return message;
    }

}
