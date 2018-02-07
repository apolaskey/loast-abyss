package com.rustedshark.mud.injection.services.login;

import com.rustedshark.mud.security.SessionAuthentication;
import org.springframework.web.socket.WebSocketSession;

import java.util.Optional;

public interface LoginService {
    Optional<SessionAuthentication> sessionAuthenticated(WebSocketSession session);
    SessionAuthentication processCredential(WebSocketSession session, String accountName, String password);
}
