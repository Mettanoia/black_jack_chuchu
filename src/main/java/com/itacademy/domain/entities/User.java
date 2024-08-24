package com.itacademy.domain.entities;

import java.util.OptionalInt;
import java.util.Set;

public interface User {

    OptionalInt hit(Card card);

    void stand();

    Integer getId();

    Integer getRankingPosition();

    String getName();

    double getCurrentBet();

    Set<Card> getHand();

    boolean isStanding();

    void setCurrentBet(double currentBet);

    void setHand(java.util.Set<Card> hand);

}
