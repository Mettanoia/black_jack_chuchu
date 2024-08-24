package com.itacademy.games.use_cases;

import com.itacademy.domain.entities.CardGameState;
import com.itacademy.domain.entities.User;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface HitUseCase {
    Mono<Void> exec(User user, CardGameState cardGameState);
}
