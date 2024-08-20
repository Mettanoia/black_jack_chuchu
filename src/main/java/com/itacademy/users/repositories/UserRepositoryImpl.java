package com.itacademy.users.repositories;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Spring annotations
@Component
@Primary

// Lombok annotations
@RequiredArgsConstructor

class UserRepositoryImpl implements UserRepository {

    private final UserGateway userGateway;

    @Override
    public Flux<User> findAll() {
        return userGateway.findAll().map(UserMappers::toDomain);
    };

    @Override
    public Mono<User> save(User user) {
        return userGateway.save(UserMappers.toEntity(user)).map(UserMappers::toDomain);
    };

    @Override
    public Mono<User> findById(Integer id) {
        return userGateway.findById(Long.valueOf(id)).map(UserMappers::toDomain);
    }

    @Override
    public Flux<User> saveAll(Publisher<User> entityStream) {
        return userGateway
                .saveAll(Flux.from(entityStream)
                        .map(UserMappers::toEntity))
                .map(UserMappers::toDomain);
    }

    @Override
    public Flux<User> findUsersByGame_Id(Integer id) {
        return userGateway.findUsersByGame_Id(Long.valueOf(id)).map(UserMappers::toDomain);
    }

}
