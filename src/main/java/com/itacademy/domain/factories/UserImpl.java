package com.itacademy.domain.factories;

import com.itacademy.domain.entities.Card;
import com.itacademy.domain.entities.User;
import lombok.*;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
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

    @Setter(AccessLevel.NONE)
    private boolean isBusted = false;

    @Override
    public int hit(Card cardImpl){
        if (isStanding || isBusted)
            throw new IllegalStateException("Cannot call stand when already standing."); // Instead of an Optional.empty because this is a violation of the contract.

        getHand().add(cardImpl); // Hand is updated here

        int handScore = getHandScore();

        if (handScore == 21)
            isStanding = true; // He stands automatically if he has a blackjack

        return handScore;

    }

    @Override
    public int getHandScore() {

        Predicate<Card> isAce = cardInHand -> cardInHand.getRank() == Card.Rank.ACE;

        ToIntFunction<Card> cardValueWhenAceLow = cardInHand -> cardInHand.getRankValue(false);

        ToIntFunction<Card> cardValueWhenAceHigh = cardInHand -> cardInHand.getRankValue(true);

        int totalHandScoreWithoutAces = hand.stream()
                .filter(isAce.negate())
                .mapToInt(cardValueWhenAceLow)
                .sum();

        int totalAces = (int) hand.stream()
                .filter(isAce)
                .count();

        return IntStream.rangeClosed(0, totalAces)
                .map(i -> {
                    int acesAsHigh = i;
                    int acesAsLow = totalAces - i;
                    return totalHandScoreWithoutAces + acesAsHigh * Card.Rank.ACE.getHighValue() + acesAsLow * Card.Rank.ACE.getLowValue();
                })
                .filter(score -> score <= 21)
                .max()
                .orElseGet(() -> {
                    isBusted = true; // Side effect: mark the user as busted
                    return totalHandScoreWithoutAces + totalAces * Card.Rank.ACE.getLowValue();
                });
    }

    @Override
    public void stand(){
        if (isStanding || isBusted)
            throw new IllegalStateException("Cannot call stand when already standing.");

        isStanding = true;

    }

}