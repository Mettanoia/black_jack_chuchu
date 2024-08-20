package com.itacademy.games.api;

import com.itacademy.domain.entities.Game;
import com.itacademy.games.use_cases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;
import static reactor.core.publisher.Mono.just;

@RequiredArgsConstructor
@Configuration
public class GamesApiConfig {

    private final FindAllGamesUseCase findAllGamesUseCase;
    private final SaveNewGameUseCase saveNewGameUseCase;
    private final FindGameByIdUseCase findGameByIdUseCase;
    private final DeleteGameByIdUseCase deleteGameByIdUseCase;

    @Bean
    public RouterFunction<?> gamesApiRouterFunction() {

        return route(POST("/api/games/new"), this::saveNewGame)
                .andRoute(GET("/api/games/{id}"), this::findGameById)
                .andRoute(GET("/api/games"), request -> ok().body(findAllGamesUseCase.exec(), Game.class))
                .andRoute(DELETE("/api/games/{id}"), this::deleteGameById);

    }

    private Mono<ServerResponse> deleteGameById(ServerRequest request) {
        return deleteGameByIdUseCase.exec(Integer.parseInt(request.pathVariable("id")))
                .then(noContent().build());
    }

    private Mono<ServerResponse> saveNewGame(ServerRequest request) {
        return saveNewGameUseCase.exec()
                .flatMap(savedGame ->
                        created(URI.create("/api/games/" + savedGame.getId()))
                                .body(savedGame, Game.class));
    }

    private Mono<ServerResponse> findGameById(ServerRequest request) {
        return findGameByIdUseCase.exec(Long.valueOf(request.pathVariable("id")))
                .flatMap(game -> ok().body(just(game), Game.class));
    }


}
