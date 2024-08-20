package com.itacademy.users.repositories;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@Table(value = "users")
public final class UserEntity {

    @Id
    private final Long id;
    private final Integer rankingPosition;
    private final String name;

}
