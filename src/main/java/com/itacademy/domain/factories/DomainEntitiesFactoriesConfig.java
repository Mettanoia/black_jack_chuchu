package com.itacademy.domain.factories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DomainEntitiesFactoriesConfig {

    @Bean
    public CardFactory cardFactory() {
        return CardImpl::new;
    }

    @Bean
    public GameFactory gameFactory() {
        return (id, winnerId) -> new GameImpl();
    }

    @Bean
    public UserFactory userFactory() {
        return UserImpl::new;
    }

    @Bean
    public CardGameStateFactory cardGameStateFactory() {
        return (users, pot, deck, currentPlayerId) -> {
            BlackjackCardGameState gameState = new BlackjackCardGameState(users, pot, deck);
            gameState.setCurrentPlayerId(currentPlayerId);
            return gameState;
        };
    }









}
