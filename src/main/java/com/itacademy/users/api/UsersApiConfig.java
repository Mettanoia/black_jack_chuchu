package com.itacademy.users.api;

import com.itacademy.domain.entities.UserImpl;
import com.itacademy.users.use_cases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;
import static reactor.core.publisher.Mono.just;

@Configuration
@RequiredArgsConstructor
class UsersApiConfig {

    private final UpdateUserUseCase updateUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final GetUsersRankingUseCase getUsersRankingUseCase;

    @Bean
    public RouterFunction<?> usersApiRouterFunction() {
        return route(
                PUT("/api/users/{id}"),
                this::updateUserName
        ).andRoute(
                GET("/api/users/ranking"),
                this::getUsersRanking
        );
    }

    private Mono<ServerResponse> updateUserName(ServerRequest request) {
        return request.bodyToMono(UserImpl.class)
                .flatMap(user -> updateUserUseCase.exec(user))
                .flatMap(user -> ok().body(just(user), UserImpl.class))
                .onErrorResume(this::handleException);
    }

    private Mono<ServerResponse> getUsersRanking(ServerRequest request) {
        return ok().body(getUsersRankingUseCase.exec(), UserImpl.class);
    }

    private Mono<ServerResponse> handleException(Throwable e) {

        ResponseStatusException ex;
        if (e instanceof ResponseStatusException) {
            ex = (ResponseStatusException) e;
        } else {
            ex = new ResponseStatusException(INTERNAL_SERVER_ERROR, "Internal server error", e);
        }

        if (ex.getStatusCode() == NOT_FOUND) {
            return notFound().build();
        } else if (ex.getStatusCode() == BAD_REQUEST) {
            return badRequest().body(just("Invalid input provided"), String.class);
        } else if (ex.getStatusCode() == INTERNAL_SERVER_ERROR) {
            return status(INTERNAL_SERVER_ERROR).body(just("Internal server error"), String.class);
        } else {
            return status(ex.getStatusCode()).body(just(ex.getReason()), String.class);
        }

    }
}
