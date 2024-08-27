package com.itacademy.domain.factories;

import com.itacademy.domain.entities.Game;

@FunctionalInterface
public interface GameFactory {
    Game createGame(Integer id, Integer winnerId);
}