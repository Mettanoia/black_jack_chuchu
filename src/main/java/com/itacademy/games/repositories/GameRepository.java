package com.itacademy.games.repositories;

import com.itacademy.domain.entities.Game;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface GameRepository {

    Flux<Game> findAll();

    Mono<Game> findById(Long id);

    Mono<Game> save(Game entity);

    Flux<Game> saveAll(Publisher<Game> entities);

    Mono<Void> deleteById(Integer id);

    Flux<Game> findGamesByUser_Id(Integer id);

}
