package com.itacademy.games.websocket_handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Flux.error;
import static reactor.core.publisher.Mono.just;

@Service

@RequiredArgsConstructor
final class GamesWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper objectMapper;


    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive().concatMap(message -> {

            // TODO some processing

            return session.send(just(session.textMessage("")));

        }).then();

    }


}
