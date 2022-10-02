package xyz.danizz.wdym.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.danizz.wdym.data.repository.GameRepositoryImpl
import xyz.danizz.wdym.domain.usecase.CreateGameUseCase
import xyz.danizz.wdym.domain.repository.GameRepository
import xyz.danizz.wdym.domain.usecase.GetAvailableCodeUseCase

@Configuration
class BeanProvider {

    @Bean
    fun gameRepository(): GameRepository {
        return GameRepositoryImpl()
    }

    @Bean
    fun createGameUseCase(
        gameRepository: GameRepository,
        getAvailableCodeUseCase: GetAvailableCodeUseCase
    ): CreateGameUseCase {
        return CreateGameUseCase(gameRepository, getAvailableCodeUseCase)
    }

    @Bean
    fun getAvailableCodeUseCase(gameRepository: GameRepository): GetAvailableCodeUseCase {
        return GetAvailableCodeUseCase(gameRepository)
    }
}
