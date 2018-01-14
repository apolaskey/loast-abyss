package com.rustedshark.mud.components;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;

/**
 * Responsible for creating and retrieving a game session
 */
@Service
public class GameSessionService {
    public HashSet<WebSocketSession> _sessions = new HashSet<>();
}
