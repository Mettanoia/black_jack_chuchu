package com.itacademy.games.use_cases;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface HitUseCase {
    Mono<Void> exec();
}
