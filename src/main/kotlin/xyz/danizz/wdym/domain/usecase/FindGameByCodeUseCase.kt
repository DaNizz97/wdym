package xyz.danizz.wdym.domain.usecase

import xyz.danizz.wdym.domain.model.Game
import xyz.danizz.wdym.domain.repository.GameRepository

class FindGameByCodeUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(code: Int): Game? {
        return gameRepository.getByCode(code)
    }
}