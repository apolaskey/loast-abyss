package com.rustedshark.mud;

import com.rustedshark.mud.handlers.GameWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@EnableWebSocket
public class ApplicationContext extends SpringBootServletInitializer implements WebSocketConfigurer {

    private final GameWebSocketHandler _gameSocketHandler;

    @Autowired
    public ApplicationContext(GameWebSocketHandler handler) {
        this._gameSocketHandler = handler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(_gameSocketHandler, "/game").setAllowedOrigins("*");
    }

    /**
     * Backend Main, nothing really more should be needed here
     * @param args {@link String} arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationContext.class, args);
    }
}
