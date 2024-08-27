package com.itacademy.domain.factories;

import com.itacademy.domain.entities.Card;
import com.itacademy.domain.entities.CardGameState;
import com.itacademy.domain.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@FunctionalInterface
public interface CardGameStateFactory {
    CardGameState createCardGameState(List<User> users, Map<User, Double> pot, Stack<Card> deck, int currentPlayerId);
}