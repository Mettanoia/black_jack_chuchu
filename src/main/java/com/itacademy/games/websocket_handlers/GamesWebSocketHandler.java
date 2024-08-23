package com.itacademy.games.websocket_handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.domain.entities.Game;
import com.itacademy.domain.entities.GameState;
import com.itacademy.domain.entities.PlayerMessage;
import com.itacademy.domain.entities.User;
import com.itacademy.games.repositories.GameGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static reactor.core.publisher.Mono.*;


@Service
final class GamesWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper om = new ObjectMapper();
    private final Map<Integer, WebSocketSession> playerSessions= new HashMap();
    private User currentPlayer;

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        return session.receive()
                .concatMap(message -> getPlayerMessage(session, message))
                .doOnNext(playerMessage -> {

                    GameState gameState =
                            GameState.getInstance()
                                    .orElseThrow(() -> new IllegalStateException("Game state is not initialized"));

                    // TODO Update currentPlayer and then GameState using currentPlayer

                    playerSessions.forEach((k, playerSession) -> {

                        WebSocketMessage webSocketMessage = playerSession.textMessage(getPayload(playerMessage));
                        playerSession.send(just(webSocketMessage));

                    });

                }).then();

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
