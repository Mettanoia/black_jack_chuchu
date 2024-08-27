package com.itacademy.domain.config;

import com.itacademy.domain.entities.Card;
import com.itacademy.domain.entities.CardGameState;
import com.itacademy.domain.factories.CardFactory;
import com.itacademy.domain.factories.CardGameStateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration

@RequiredArgsConstructor
public class CardGameStateConfig {

    private final CardFactory cardFactory;
    private final CardGameStateFactory cardGameStateFactory;

    @Bean
    CardGameState cardGameState() {
        return cardGameStateFactory.createCardGameState(List.of(), Map.of(), deck(), 0);
    }

    private Stack<Card> deck() {

        Stack<Card> deck = Stream.of(Card.Suit.values())
                .flatMap(suit -> Stream.of(Card.Rank.values())
                        .map(rank -> cardFactory.createCard(rank, suit)))
                .collect(Collectors.toCollection(Stack::new));

        Collections.shuffle(deck);

        return deck;

    }


}
