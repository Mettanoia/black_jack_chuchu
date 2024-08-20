package com.itacademy.games.repositories;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface GameGateway extends ReactiveCrudRepository<GameEntity, Long> {

    @Override
    Flux<GameEntity> findAll();

    @Override
    Mono<GameEntity> findById(Long id);

    @Override
    <S extends GameEntity> Mono<S> save(S entity);

    @Override
    <S extends GameEntity> Flux<S> saveAll(Publisher<S> entityStream);

    @Override
    Mono<Void> deleteById(Long id);

    @Query("SELECT g.* FROM games g " +
            "JOIN game_participation gp ON g.id = gp.game_id " +
            "WHERE gp.user_id = :id")
    Flux<GameEntity> findGamesByUser_Id(Long id);

}
