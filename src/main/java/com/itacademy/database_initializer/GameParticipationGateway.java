package com.itacademy.database_initializer;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
interface GameParticipationGateway extends ReactiveCrudRepository<GameParticipationEntity, Long> {

    @Override
    <S extends GameParticipationEntity> Mono<S> save(S entity);

}
