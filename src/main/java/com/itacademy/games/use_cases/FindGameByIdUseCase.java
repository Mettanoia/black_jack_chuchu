package com.itacademy.games.use_cases;

import com.itacademy.domain.entities.Game;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface FindGameByIdUseCase {
    Mono<Game> exec(long gameId);
}
