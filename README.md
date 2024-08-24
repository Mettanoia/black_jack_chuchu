### Blackjack Game Rules and User States

#### Introduction
This document outlines the assumed rules of Blackjack that our implementation follows and describes the possible states that a `User` (representing a player) can be in throughout the game, based on the latest implementation.

#### Blackjack Game Rules
We assume the following standard rules for Blackjack:

1. **Objective**:
   - The goal is to have a hand value as close to 21 as possible without exceeding it. The hand that is closest to 21 wins. If a player's hand exceeds 21, they bust and lose the round.

2. **Card Values**:
   - Number cards (2-10): Worth their face value.
   - Face cards (Jack, Queen, King): Each worth 10 points.
   - Ace: Worth 1 or 11 points, depending on what benefits the hand most.

3. **Initial Deal**:
   - Each player, including the dealer, is dealt two cards at the start of the game. Players’ cards are dealt face up, while the dealer has one card face up and one card face down.

4. **Player Actions**:
   - **Hit**: The player may request additional cards to improve their hand. If the player’s hand exceeds 21, they bust and lose the round.
   - **Stand**: The player decides to stop taking cards, and the turn passes to the dealer.

5. **Dealer Actions**:
   - The dealer must hit until their hand totals 17 or higher. If the dealer busts, all remaining players win that round.

6. **Winning**:
   - The player wins if their hand value is higher than the dealer's without exceeding 21. If the player and the dealer have the same hand value, it's a push (a tie), and the player's bet is returned.

#### User States
A `User` object in our implementation can transition through several distinct states based on the actions taken during the game. Below is a description of these states:

1. **Initial State**:
   - **Attributes**:
     - The `User` has been created but has not yet taken any game actions.
     - The player's `hand` is empty or initialized.
     - `isStanding`: `false`.
     - `isBusted`: `false`.
   - **Possible Transitions**:
     - The player can `hit` to receive their first cards.

2. **Playing State**:
   - **Attributes**:
     - The player has a `hand` of cards and has not yet chosen to `stand`.
     - `isStanding`: `false`.
     - `isBusted`: `false`.
   - **Description**:
     - The player is actively participating in the round, deciding whether to `hit` or `stand`.
   - **Possible Transitions**:
     - The player may continue to `hit`, adding more cards, or choose to `stand`.

3. **Standing State**:
   - **Attributes**:
     - The player has chosen to stop taking cards, either by deciding to `stand` or by achieving a hand value of exactly 21 during a `hit`.
     - `isStanding`: `true`.
     - `isBusted`: `false`.
   - **Description**:
     - The player has decided their hand is strong enough to compete against the dealer and will not take any more cards.
   - **Possible Transitions**:
     - The round will conclude once the dealer's turn is over.

4. **Busted State**:
   - **Attributes**:
     - The player's hand value exceeds 21.
     - `isStanding`: `false`.
     - `isBusted`: `true`.
   - **Description**:
     - The player has exceeded a hand value of 21 and has automatically lost the round.
   - **Possible Transitions**:
     - No further actions are possible; the player has lost the round.

5. **Error State**:
   - **Scenario**:
     - The player attempts an illegal action, such as hitting after standing, hitting after busting, or trying to stand after busting.
   - **Description**:
     - An exception is thrown to indicate a violation of the game rules. The exception handling logic will depend on the specific game implementation.
   - **Possible Transitions**:
     - The game will handle the exception based on the specific implementation (e.g., logging the error, informing the player).

### Game Flow Based on User Actions

- **Starting the Game**: The player is dealt two cards and can choose to `hit` or `stand`.
- **Hitting**: Each time the player hits, a new card is added to their hand. If the total hand value exceeds 21, the `isBusted` flag is set to `true`. If the hand value reaches exactly 21, the `isStanding` flag is automatically set to `true`.
- **Standing**: When the player stands, they can no longer take any further actions, and their turn ends. The dealer then plays according to the rules.
