package com.rustedshark.mud.injection.services.login;

import com.google.common.collect.Sets;
import com.rustedshark.mud.data.persistence.GameAccountDao;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.inject.Inject;
import java.util.*;

@Service
public class EmbeddedLoginService implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(EmbeddedLoginService.class);
    private static final Set<String> authorizedSessions = Sets.newConcurrentHashSet();

    @Inject
    private Jdbi repository;

    @Override
    public Optional<SessionAuthentication> sessionAuthenticated(WebSocketSession session) {
        if(authorizedSessions.) {
            return Optional.of(_authenticatedSessions.get(session.getId()));
        }
        return Optional.empty();
    }

    @Override
    public SessionAuthentication processCredential(WebSocketSession session, String accountName, String password) {
        return _jbdi.withExtension(GameAccountDao.class, dao -> {
            return null; //new SessionAuthentication(accountName, UUID.randomUUID().toString());
        });
    }
}
