package com.itacademy.domain.entities;

public interface CardGameState {
    java.util.List<User> getUsers();

    java.util.Map<User, Double> getPot();

    java.util.Stack<Card> getDeck();

    int getCurrentPlayerId();

    void setCurrentPlayerId(int currentPlayerId);
}
