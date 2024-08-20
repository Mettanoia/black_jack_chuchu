package com.itacademy.domain.entities;

public final class UserFactory {

    static User of(Integer id, Integer rankingPosition, String name) {
        return new UserImpl(id, rankingPosition, name);
    }

    static User of() {
        return new UserImpl();
    }

}
