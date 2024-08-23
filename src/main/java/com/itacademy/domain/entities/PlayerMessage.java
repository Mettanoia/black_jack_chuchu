package com.itacademy.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class PlayerMessage {

    private final User player;
    private final BlackjackAction playerAction;

    public enum BlackjackAction {
        HIT,
        STAND,
    }

}
