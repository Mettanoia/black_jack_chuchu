package com.itacademy.users.use_cases;

import com.itacademy.domain.entities.User;
import reactor.core.publisher.Mono;

public interface UpdateUserUseCase {
    Mono<User> exec(User user);
}
