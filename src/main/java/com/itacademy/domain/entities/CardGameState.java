package com.itacademy.domain.entities;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public interface CardGameState {

    List<User> getUsers();

    Map<User, Double> getPot();

    Stack<Card> getDeck();

    int getCurrentPlayerId();

    void setCurrentPlayerId(int currentPlayerId);

    void startGame();

    void finishGame();

    // Method to deal initial cards to each player at the start of the game
    void dealInitialCards();

    default void playerHit(User player, Card cardImpl) {
        player.hit(cardImpl);
    }

    default void playerStand(User player) {
        player.stand();
    }

    default boolean isPlayerBust(User player){
        return player.isBusted();
    }

    default boolean hasPlayerBlackjack(User player) {
        return (player.getHandScore() == 21);
    }

    // Method to move to the next player's turn
    void moveToNextPlayer();

    // Method to check if the game is over (all players have stood or busted)
    boolean isGameOver();

    // Method to determine the winner(s) of the game based on final scores
    List<User> determineWinners();

    // Method to reset the game state for a new round
    default void resetGame() {
        throw new UnsupportedOperationException("Method not implemented.");
    };

}
