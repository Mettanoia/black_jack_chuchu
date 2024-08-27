package com.itacademy.users.use_cases;

import com.itacademy.domain.entities.User;
import reactor.core.publisher.Flux;

public interface FindAllUsersUseCase {
    Flux<User> exec();
}
