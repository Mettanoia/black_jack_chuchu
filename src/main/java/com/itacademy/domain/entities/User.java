package com.itacademy.domain.entities;

public interface User {
    void hit(Card card);

    void stand();

    Integer getId();

    Integer getRankingPosition();

    String getName();

    double getCurrentBet();

    java.util.Set<Card> getHand();

    boolean isStanding();

    void setCurrentBet(double currentBet);

    void setHand(java.util.Set<Card> hand);
}
