package com.itacademy.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class PlayerAction {

    private final String action;
    private final int playerId;

}
