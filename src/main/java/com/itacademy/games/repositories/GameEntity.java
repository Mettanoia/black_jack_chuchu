package com.itacademy.games.repositories;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@Table(value = "games")
public final class GameEntity {

    @Id
    private final Long id;

}
