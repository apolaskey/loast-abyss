package com.rustedshark.mud.injection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisServer;
import redis.embedded.RedisServerBuilder;

import javax.annotation.PostConstruct;

@Configuration
public class RedisConfig {

    @Value("${mud.redis.port:6380}")
    private int _port;
    private RedisServerBuilder _builder = new RedisServerBuilder();

    @Bean
    public RedisServer redisServer() {
        RedisServer springRedisServer = new RedisServer("localhost", _port);
        return springRedisServer;
    }

    @PostConstruct
    public void start() {
        _builder.reset();
        _builder.port(_port)
                .setting("bind 127.0.0.1")
                .setting("maxmemory 256M")
                .build().start();
    }

}
