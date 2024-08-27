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
    private final GameMappers gameMappers;

    @Override
    public Flux<Game> findAll() {
        return gameGateway.findAll().flatMap(gameMappers::toDomain);
    }

    @Override
    public Mono<Game> save(Game game) {
        return gameGateway
                .save(gameMappers.toEntity(game))
                .flatMap(gameMappers::toDomain);
    }

    @Override
    public Mono<Game> findById(Long id) {
        return gameGateway.findById(id).flatMap(gameMappers::toDomain);
    }

    @Override
    public Flux<Game> saveAll(Publisher<Game> games) {
        return gameGateway.saveAll(
                        Flux.from(games)
                                .map(gameMappers::toEntity)
                )
                .flatMap(gameMappers::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return gameGateway.deleteById(Long.valueOf(id));
    }

    @Override
    public Flux<Game> findGamesByUser_Id(Integer id) {
        return gameGateway.findGamesByUser_Id(Long.valueOf(id)).flatMap(gameMappers::toDomain);
    }

}
