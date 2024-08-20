package com.itacademy.games.repositories;

import com.itacademy.domain.entities.Game;

import java.util.Objects;

final class GameMappers {

    public static Game toDomain(GameEntity entity) {
        return new Game(Math.toIntExact(Objects.requireNonNull(entity.getId())));
    }

    public static GameEntity toEntity(Game game) {
        return new GameEntity(game.getId() == null ? null : Long.valueOf(game.getId()));
    }

}
