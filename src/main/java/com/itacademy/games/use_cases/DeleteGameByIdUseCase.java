package com.itacademy.games.use_cases;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteGameByIdUseCase {
    Mono<Void> exec(int gameId);
}
