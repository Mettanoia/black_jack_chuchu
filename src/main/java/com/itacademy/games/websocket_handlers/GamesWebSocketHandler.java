package com.itacademy.games.websocket_handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.domain.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;

import static reactor.core.publisher.Mono.*;


@Service

@RequiredArgsConstructor
final class GamesWebSocketHandler implements WebSocketHandler {

    private final CardGameState cardGameState;
    private final ObjectMapper om = new ObjectMapper();
    private final Map<Integer, WebSocketSession> playerSessions= new HashMap();
    private User currentPlayer;

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        return session.receive()
                .concatMap(message -> getPlayerMessage(session, message))
                .doOnNext(playerMessage -> {

                    switch (playerMessage.getPlayerAction()) {

                        case STAND -> null;
                        case HIT -> null

                    }

                    broadcastGameState(playerMessage);

                }).then();

    }

    private void broadcastGameState(PlayerMessage playerMessage) {

        playerSessions.forEach((k, playerSession) -> {

            WebSocketMessage webSocketMessage = playerSession.textMessage(getPayload(playerMessage));
            playerSession.send(just(webSocketMessage));

        });

    }

    private String getPayload(PlayerMessage playerMessage) {

        try {
            return om.writeValueAsString(playerMessage);
        } catch (JsonProcessingException e) {throw new RuntimeException(e);}

    }

    private Mono<PlayerMessage> getPlayerMessage(WebSocketSession session, WebSocketMessage message) {

        PlayerMessage playerMessage;
        try {

            playerMessage = om.readValue(message.getPayload().asInputStream(), PlayerMessage.class);

            // This invariant assures that no messages are processed outside the current player's turn.
            if (!Objects.equals(playerMessage.getPlayer().getId(), currentPlayer.getId()))
                return error(new RuntimeException("Client attempted access while game was in an illegal state."));

            playerSessions.putIfAbsent(playerMessage.getPlayer().getId(), session);

            return just(playerMessage);

        } catch (IOException e) {return error(new RuntimeException(e));}

    }

}
