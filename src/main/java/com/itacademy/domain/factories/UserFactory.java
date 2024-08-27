package com.itacademy.domain.factories;

import com.itacademy.domain.entities.User;

@FunctionalInterface
public interface UserFactory {
    User createUser(Integer id, Integer rankingPosition, String name);
}