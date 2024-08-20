package com.itacademy.users.use_cases;

import reactor.core.publisher.Flux;

public interface FindAllUsersUseCase {
    Flux<User> exec();
}
