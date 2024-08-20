package com.itacademy.games.repositories;

import com.itacademy.domain.entities.Game;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Primary

@RequiredArgsConstructor

class GameRepositoryImpl implements GameRepository {

    private final GameGateway gameGateway;

    @Override
    public Flux<Game> findAll() {
        return gameGateway.findAll().map(GameMappers::toDomain);
    }

    @Override
    public Mono<Game> save(Game entity) {
        return gameGateway
                .save(GameMappers.toEntity(entity))
                .map(GameMappers::toDomain);
    }

    @Override
    public Mono<Game> findById(Long id) {
        return gameGateway.findById(id).map(GameMappers::toDomain);
    }

    @Override
    public Flux<Game> saveAll(Publisher<Game> entities) {
        return gameGateway.saveAll(
                        Flux.from(entities)
                                .map(GameMappers::toEntity)
                )
                .map(GameMappers::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return gameGateway.deleteById(Long.valueOf(id));
    }

    @Override
    public Flux<Game> findGamesByUser_Id(Integer id) {
        return gameGateway.findGamesByUser_Id(Long.valueOf(id)).map(GameMappers::toDomain);
    }

}
