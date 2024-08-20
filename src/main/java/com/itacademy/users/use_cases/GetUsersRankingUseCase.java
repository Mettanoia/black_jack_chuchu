package com.itacademy.users.use_cases;

import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetUsersRankingUseCase {
    Flux<User> exec();
}
