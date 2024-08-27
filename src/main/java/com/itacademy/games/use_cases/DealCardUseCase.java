package com.itacademy.games.use_cases;

import com.itacademy.domain.entities.Card;
import com.itacademy.domain.entities.User;

import java.util.Stack;

@FunctionalInterface
public interface DealCardUseCase {
    void exec(User user, Stack<Card> deck);
}
