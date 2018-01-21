package com.rustedshark.mud.services.session;

import com.rustedshark.mud.models.ClientSessionCredential;
import com.rustedshark.mud.models.player.PlayerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Service to manage hot player sessions
 * Notes: Not responsible for determining when to add a session into redis, only to facilitate
 */
@Service
public class RedisSessionServiceImpl implements SessionService {

    private final RedisTemplate<String, Object> _redisTemplate;
    private final HashOperations _hashOps;

    public static final String PlayerSessionKey = "PlayerSession";

    @Autowired public RedisSessionServiceImpl(RedisTemplate<String, Object> redisTemplates) {
        _redisTemplate = redisTemplates;
        _hashOps = _redisTemplate.opsForHash();
    }

    @Override
    public Optional<PlayerSession> sessionExists(WebSocketSession webSocketSession) {
        PlayerSession session = (PlayerSession) _hashOps.get(PlayerSessionKey, webSocketSession.getId());
        if(session == null) {
            return Optional.empty();
        } else {
            return Optional.of(session);
        }
    }

    @Override
    public PlayerSession createGameSession(final WebSocketSession webSocketSession) {
        return sessionExists(webSocketSession).orElseGet(new Supplier<PlayerSession>() {
            @Override
            public PlayerSession get() {
                PlayerSession session = new PlayerSession();
                _hashOps.put(PlayerSessionKey, webSocketSession.getId(), session);
                return session;
            }
        });
    }

    @Override
    public List<PlayerSession> activeSessions() {
        Map<String, PlayerSession> activeSessions = _hashOps.entries(PlayerSessionKey);
        return null;
    }
}
