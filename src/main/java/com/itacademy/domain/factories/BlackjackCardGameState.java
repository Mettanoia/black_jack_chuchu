package com.itacademy.domain.factories;

import com.itacademy.domain.entities.Card;
import com.itacademy.domain.entities.CardGameState;
import com.itacademy.domain.entities.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@Getter
@RequiredArgsConstructor
final class BlackjackCardGameState implements CardGameState {

    private final List<User> users;
    private final Map<User, Double> pot;
    private final Stack<Card> deck;


    @Setter
    private int currentPlayerId;

    @Override
    public void startGame() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void finishGame() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void dealInitialCards() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void moveToNextPlayer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean isGameOver() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<User> determineWinners() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}