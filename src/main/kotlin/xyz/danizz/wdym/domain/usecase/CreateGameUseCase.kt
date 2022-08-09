package xyz.danizz.wdym.domain.usecase

import org.springframework.stereotype.Component
import xyz.danizz.wdym.domain.model.Game
import xyz.danizz.wdym.domain.model.GameType
import xyz.danizz.wdym.domain.model.MemesBasedGame
import xyz.danizz.wdym.domain.model.SituationBasedGame
import kotlin.random.Random

/**
 * TODO: remove @Component from here
 */
@Component
class CreateGameUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(name: String, type: GameType): Game {
        val code = Random.nextInt(100000, 999999)

        return when (type) {
            GameType.MEMES -> MemesBasedGame(name, code)
            GameType.SITUATION -> SituationBasedGame(name, code)
        }
    }
}