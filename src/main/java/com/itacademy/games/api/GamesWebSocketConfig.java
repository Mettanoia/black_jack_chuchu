package com.itacademy.games.api;

import com.itacademy.games.use_cases.HitUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.Map;


@RequiredArgsConstructor
@Configuration
class GamesWebSocketConfig {

    private final WebSocketHandler webSocketHandler;

    @Bean
    public HandlerMapping gamesWebSocketHandlerMapping() {

        Map<String, WebSocketHandler> endpoints = Map.of(
                "/ws/games/hit", webSocketHandler
        );

        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setUrlMap(endpoints);

        return simpleUrlHandlerMapping;

    }

}
