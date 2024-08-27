package com.itacademy.games.use_cases;

import com.itacademy.domain.entities.CardGameState;
import com.itacademy.domain.factories.GameFactory;
import com.itacademy.games.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

@RequiredArgsConstructor
class GamesUseCasesConfig {

    private final GameFactory gameFactory;
    private final CardGameState cardGameState; // This implementation of the configuration needs the state to be injected

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
        return () -> gameRepository.save(gameFactory.createGame(null, null));
    }

    @Bean
    public DeleteGameByIdUseCase deleteGameByIdUseCase(GameRepository gameRepository) {
        return gameId -> gameRepository.deleteById(gameId);
    }

    @Bean
    public HitUseCase hitUseCase(GameRepository gameRepository) {
        return (user, cardGameState) -> null;
    }

    @Bean
    public StandUseCase standUseCase() {
        return (userId) -> cardGameState.getUsers().stream()
                .filter(user -> user.getId() == cardGameState.getCurrentPlayerId())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No user is the current user."))
                .stand();
    }

    @Bean
    public DealCardUseCase dealCardUseCase() {
        return (user, deck) -> user.getHand().add(deck.pop());
    }

}
