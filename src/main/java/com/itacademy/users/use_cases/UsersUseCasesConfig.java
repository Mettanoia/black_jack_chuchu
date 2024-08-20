package com.itacademy.users.use_cases;

import com.itacademy.users.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UsersUseCasesConfig {

    @Bean
    FindAllUsersUseCase findAllUsersUseCase(UserRepository userRepository) {
        return () -> userRepository.findAll();
    }

    @Bean
    UpdateUserUseCase updateUserUseCase(UserRepository userRepository) {
        return user -> userRepository.save(user);
    }

    @Bean
    FindUserByIdUseCase findUserByIdUseCase(UserRepository userRepository) {
        return userId -> userRepository.findById(userId);
    }

    @Bean
    GetUsersRankingUseCase getUsersRankingUseCase(UserRepository userRepository) {
        return () -> findAllUsersUseCase(userRepository).exec().sort((a, b) -> b.getRankingPosition() - a.getRankingPosition());
    }

}
