package com.itacademy.domain.entities;

import java.util.Set;

public interface User {

    int hit(Card cardImpl);

    int getHandScore();

    void stand();

    Integer getId();

    Integer getRankingPosition();

    String getName();

    double getCurrentBet();

    Set<Card> getHand();

    boolean isStanding();

    boolean isBusted();

    void setCurrentBet(double currentBet);

    void setHand(Set<Card> hand);
}
