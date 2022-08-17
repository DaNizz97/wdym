package xyz.danizz.wdym.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.danizz.wdym.data.repository.GameRepositoryImpl
import xyz.danizz.wdym.domain.usecase.CreateGameUseCase
import xyz.danizz.wdym.domain.repository.GameRepository

@Configuration
class BeanProvider {

    @Bean
    fun gameRepository(): GameRepository {
        return GameRepositoryImpl()
    }

    @Bean
    fun createGameUseCase(gameRepository: GameRepository): CreateGameUseCase {
        return CreateGameUseCase(gameRepository)
    }
}
