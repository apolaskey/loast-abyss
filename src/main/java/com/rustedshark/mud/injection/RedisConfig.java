package com.rustedshark.mud.injection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisServer;
import redis.embedded.RedisServerBuilder;

import javax.annotation.PostConstruct;

@Configuration
public class RedisConfig {

    @Value("${mud.redis.port:6379}")
    private int _port;

    @Bean
    public RedisServer redisServer() {
        return new RedisServer("localhost", _port);
    }

}
