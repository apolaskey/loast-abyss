package com.rustedshark.mud.injection.components;

import com.rustedshark.mud.data.game.messages.GameMessage;
import com.rustedshark.mud.data.game.messages.NoticeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessions {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketSessions.class);
    private static final ConcurrentHashMap<String, WebSocketSession> connectedSessions = new ConcurrentHashMap<>();

}
