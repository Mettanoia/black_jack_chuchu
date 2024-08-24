package com.itacademy.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class Card {

    private final Rank rank;

    public int getRankValue(boolean aceHigh) {

        return (rank == Rank.ACE) ?
                aceHigh ?
                        rank.getHighValue() :
                        rank.getLowValue() :
                rank.getValue();

    }

    @Getter
    @RequiredArgsConstructor
    enum Rank {

        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(1, 14);

        private final int lowValue;
        private final int highValue;

        Rank(int value) {
            this(value, value);
        }

        public int getValue() {
            return highValue;
        }

    }

}
