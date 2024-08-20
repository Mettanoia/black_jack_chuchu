package com.itacademy.database_initializer;

import com.itacademy.domain.entities.Game;
import com.itacademy.games.repositories.GameRepository;
import com.itacademy.domain.entities.UserImpl;
import com.itacademy.users.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initializeDatabase(GameRepository gameRepository,
                                                UserRepository userRepository,
                                                GameParticipationGateway gameParticipationGateway) {
        return args -> {

            // Create and save Users
            Flux<User> users = userRepository.saveAll(Flux.just(
                    new UserImpl(null, 1, "Alice"),
                    new UserImpl(null, 2, "Bob"),
                    new UserImpl(null, 3, "Charlie"),
                    new UserImpl(null, 4, "Dave"),
                    new UserImpl(null, 5, "Eve")
            ));

            // Create and save Games
            Flux<Game> games = gameRepository.saveAll(Flux.just(
                    new Game(),
                    new Game(),
                    new Game(),
                    new Game(),
                    new Game()
            ));

            // Wait for the Users and Games to be saved
            Mono<Void> setup = users.thenMany(games).then();

            // Create and save GameParticipations after Users and Games are saved
            setup.thenMany(gameParticipationGateway.saveAll(Flux.just(
                    new GameParticipationEntity(null, 1L, 1L),  // Alice -> Game A
                    new GameParticipationEntity(null, 1L, 2L),  // Bob -> Game A
                    new GameParticipationEntity(null, 2L, 1L),  // Alice -> Game B
                    new GameParticipationEntity(null, 2L, 3L),  // Charlie -> Game B
                    new GameParticipationEntity(null, 3L, 2L),  // Bob -> Game C
                    new GameParticipationEntity(null, 3L, 3L),  // Charlie -> Game C
                    new GameParticipationEntity(null, 4L, 4L),  // Dave -> Game D
                    new GameParticipationEntity(null, 5L, 5L),  // Eve -> Game E
                    new GameParticipationEntity(null, 4L, 1L),  // Alice -> Game D
                    new GameParticipationEntity(null, 5L, 2L)  // Bob -> Game E
            ))).then().block();

        };
    }
}
