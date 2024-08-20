package com.itacademy.users.repositories;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserGateway extends ReactiveCrudRepository<UserEntity, Long> {

    @Override
    Flux<UserEntity> findAll();

    @Override
    <S extends UserEntity> Mono<S> save(S entity);

    @Override
    Mono<UserEntity> findById(Long id);

    @Override
    <S extends UserEntity> Flux<S> saveAll(Publisher<S> entityStream);

    @Query("SELECT u.* FROM users u " +
            "JOIN game_participation gp ON u.id = gp.user_id " +
            "WHERE gp.game_id = :id")
    Flux<UserEntity> findUsersByGame_Id(Long id);

}
