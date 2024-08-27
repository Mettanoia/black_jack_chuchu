package com.itacademy.domain.factories;

import com.itacademy.domain.entities.Card;

@FunctionalInterface
public interface CardFactory {
    Card createCard(Card.Rank rank, Card.Suit suit);
}