package com.itacademy.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@Configuration
public class CardGameStateConfig {

    @Bean
    CardGameState cardGameState() {
        return new BlackjackCardGameState(List.of(), Map.of(), null);
    }

    @Getter
    @RequiredArgsConstructor
    static final class BlackjackCardGameState implements CardGameState {

        private final List<User> users;
        private final Map<User, Double> pot;
        private final Stack<Card> deck;

        @Setter
        private int currentPlayerId;

    }
}
