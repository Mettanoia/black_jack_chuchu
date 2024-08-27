package com.itacademy.users.repositories;

import com.itacademy.domain.entities.User;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository {

    Flux<User> findAll();

    Mono<User> save(User user);

    Mono<User> findById(Integer id);

    Flux<User> saveAll(Publisher<User> entityStream);

    Flux<User> findUsersByGame_Id(Integer gameId);

}
