package com.itacademy.games.use_cases;

import com.itacademy.domain.entities.Game;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface FindAllGamesUseCase {
    Flux<Game> exec();
}
