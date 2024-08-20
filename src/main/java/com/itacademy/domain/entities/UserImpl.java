package com.itacademy.domain.entities;

import lombok.*;

import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
final class UserImpl implements User {

    private final Integer id;
    private final Integer rankingPosition;
    private final String name;

    private double currentBet;
    private Set<Card> hand;

    @Setter(AccessLevel.NONE)
    private boolean isStanding = false;

    @Override
    public void hit(Card card){
        throw new UnsupportedOperationException();
    }

    @Override
    public void stand(){
        throw new UnsupportedOperationException();
    }

}
