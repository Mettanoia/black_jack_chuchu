package com.itacademy.domain.factories;

import com.itacademy.domain.entities.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
final class GameImpl implements Game {

    private final Integer id;
    private Integer winnerId;

}