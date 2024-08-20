package com.itacademy.users.use_cases;

import reactor.core.publisher.Mono;

public interface UpdateUserUseCase {
    Mono<User> exec(User user);
}
