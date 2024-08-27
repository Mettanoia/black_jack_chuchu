package com.itacademy.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The {@code Card} class represents a playing card with a specific {@link Rank} and {@link Suit}.
 * This class is immutable and thread-safe, ensuring that the state of a card cannot be altered
 * once it has been created. Each card is defined by its rank (e.g., Ace, King, Two) and suit
 * (e.g., Hearts, Spades). The card's rank determines its value, which can vary depending on
 * the context, particularly for the Ace, which can have either a low value (1) or a high value (11).
 * <p>
 * Example usage:
 * <pre>
 *     Card card = new Card(Card.Rank.ACE, Card.Suit.HEARTS);
 *     int value = card.getRankValue(true); // Returns 11 for a high Ace
 * </pre>
 * </p>
 *
 * @see Rank
 * @see Suit
 */
public interface Card {

    /**
     * Returns the value of the card's rank. For most ranks, this value is fixed.
     * However, for the Ace, the value can vary depending on the game context:
     * <ul>
     *     <li>If {@code aceHigh} is {@code true}, the Ace is valued at 11.</li>
     *     <li>If {@code aceHigh} is {@code false}, the Ace is valued at 1.</li>
     * </ul>
     * <p>
     * The value for other ranks is as follows:
     * <ul>
     *     <li>Two to Ten: their respective numeric values (e.g., 2, 3, 4, etc.)</li>
     *     <li>Jack, Queen, King: 10</li>
     * </ul>
     * </p>
     *
     * @param aceHigh if {@code true}, the Ace is valued as 11; if {@code false}, it is valued as 1.
     * @return the integer value associated with the card's rank, considering whether the Ace is high or low.
     */
    int getRankValue(boolean aceHigh);

    Rank getRank();

    Suit getSuit();

    public enum Suit {
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS
    }

    @Getter
    @RequiredArgsConstructor
    public
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
        JACK(10),
        QUEEN(10),
        KING(10),
        ACE(1, 11);

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
