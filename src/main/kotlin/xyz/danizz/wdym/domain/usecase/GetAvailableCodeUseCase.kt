package xyz.danizz.wdym.domain.usecase

import xyz.danizz.wdym.domain.repository.GameRepository
import kotlin.random.Random

class GetAvailableCodeUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(): Int {
        var candidateCode = Random.nextInt(100_000, 1_000_000)
        val codes = gameRepository.getAll().map { it.code }.toHashSet()
        while (codes.contains(candidateCode)) {
            candidateCode++
        }
        return candidateCode
    }
}