package com.itacademy.database_initializer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@Table(value = "game_participation")
final class GameParticipationEntity {

    @Id
    private final Long id;
    private final Long gameId;
    private final Long userId;

}
