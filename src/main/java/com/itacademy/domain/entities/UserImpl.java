package com.itacademy.domain.entities;

import lombok.*;

import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

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
    public OptionalInt hit(Card card){
        if (isStanding)
            throw new IllegalStateException("Cannot call stand when already standing."); // Instead of an Optional.empty because this is a violation of the contract.

        getHand().add(card); // Hand is updated here

        Predicate<Card> isAce = cardInHand -> cardInHand.getRank() != Card.Rank.ACE;

        int totalHandScoreWithoutAces = hand.stream()
                .filter(isAce.negate())
                .mapToInt(cardInHand -> cardInHand.getRankValue(false))
                .sum();

        int totalAces = (int) hand.stream()
                .filter(isAce)
                .count();

        return IntStream.range(0, totalAces)
                .map(i -> totalHandScoreWithoutAces + (i * Card.Rank.ACE.getHighValue() + (totalAces - i) * Card.Rank.ACE.getLowValue()))
                .filter(i -> i <= 21)
                .max();

    }

    @Override
    public void stand(){
        if (isStanding)
            throw new IllegalStateException("Cannot call stand when already standing.");

        isStanding = true;
    }

}
