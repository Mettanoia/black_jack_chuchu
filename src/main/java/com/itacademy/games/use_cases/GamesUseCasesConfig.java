package com.itacademy.games.use_cases;

import com.itacademy.domain.entities.Game;
import com.itacademy.games.repositories.GameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static reactor.core.publisher.Mono.empty;

@Configuration
class GamesUseCasesConfig {

    @Bean
    public FindAllGamesUseCase findAllGamesUseCase(GameRepository gameRepository) {
        return () -> gameRepository.findAll();
    }

    @Bean
    public FindGameByIdUseCase findGameByIdUseCase(GameRepository gameRepository) {
        return gameId -> gameRepository.findById(gameId);
    }

    @Bean
    public SaveNewGameUseCase saveNewGameUseCase(GameRepository gameRepository) {
        return () -> gameRepository.save(new Game());
    }

    @Bean
    public DeleteGameByIdUseCase deleteGameByIdUseCase(GameRepository gameRepository) {
        return gameId -> gameRepository.deleteById(gameId);
    }

    @Bean
    public HitUseCase hitUseCase(GameRepository gameRepository) {
        return session -> empty();
    }

}
