package com.itacademy.games.repositories;

import com.itacademy.domain.entities.Game;
import com.itacademy.domain.factories.GameFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static reactor.core.publisher.Mono.just;

@Component

@RequiredArgsConstructor
final class GameMappers {
    
    private final GameFactory gameFactory;
    
    public Mono<Game> toDomain(GameEntity gameEntity) {
        return just(gameFactory.createGame(Math.toIntExact(Objects.requireNonNull(gameEntity.getId())), null));
    }

    public GameEntity toEntity(Game game) {
        return new GameEntity(game.getId() == null ? null : Long.valueOf(game.getId()));
    }

}
