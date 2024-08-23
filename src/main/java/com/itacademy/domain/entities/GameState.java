package com.itacademy.domain.entities;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.Stack;


@Getter
public final class GameState {

    private final List<User> users;
    private final Stack<Card> deck;
    private int currentPlayerId;

    @Getter(AccessLevel.NONE)
    static private GameState INSTANCE;

    private GameState(List<User> users, Stack<Card> deck, int currentPlayerId) {
        this.users = users;
        this.deck = deck;
        this.currentPlayerId = currentPlayerId;
    }

    private GameState(List<User> users, Stack<Card> deck) {
        this(users, deck, users.get(0).getId());
    }

    static public GameState getInstance(List<User> users, Stack<Card> deck, int currentPlayerId) {
        if (INSTANCE == null)
            INSTANCE = new GameState(users, deck, currentPlayerId);

        return INSTANCE;

    }

    static public GameState getInstance(List<User> users, Stack<Card> deck) {
        if (INSTANCE == null)
            INSTANCE = new GameState(users, deck);

        return INSTANCE;

    }

    static public Optional<GameState> getInstance() {
        if (INSTANCE == null)
            return Optional.empty();

        return Optional.of(INSTANCE);

    }

}
