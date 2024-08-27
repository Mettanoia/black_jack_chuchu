package com.itacademy.users.repositories;

import com.itacademy.domain.entities.User;
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
    private final UserMappers userMappers;

    @Override
    public Flux<User> findAll() {
        return userGateway.findAll().map(userMappers::toDomain);
    };

    @Override
    public Mono<User> save(User user) {
        return userGateway.save(userMappers.toEntity(user)).map(userMappers::toDomain);
    };

    @Override
    public Mono<User> findById(Integer id) {
        return userGateway.findById(Long.valueOf(id)).map(userMappers::toDomain);
    }

    @Override
    public Flux<User> saveAll(Publisher<User> entityStream) {
        return userGateway
                .saveAll(Flux.from(entityStream)
                        .map(userMappers::toEntity))
                .map(userMappers::toDomain);
    }

    @Override
    public Flux<User> findUsersByGame_Id(Integer id) {
        return userGateway.findUsersByGame_Id(Long.valueOf(id)).map(userMappers::toDomain);
    }

}
