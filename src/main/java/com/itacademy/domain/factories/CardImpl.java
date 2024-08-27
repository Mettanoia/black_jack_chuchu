package com.itacademy.domain.factories;

import com.itacademy.domain.entities.Card;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
final class CardImpl implements Card {

    private final Rank rank;
    private final Suit suit;

    @Override
    public int getRankValue(boolean aceHigh) {
        return (rank == Rank.ACE) ?
                aceHigh ?
                        rank.getHighValue() :
                        rank.getLowValue() :
                rank.getValue();
    }

}