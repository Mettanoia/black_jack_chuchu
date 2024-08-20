package com.itacademy.users.use_cases;

import reactor.core.publisher.Mono;

public interface FindUserByIdUseCase {
    Mono<User> exec(Integer userId);
}
