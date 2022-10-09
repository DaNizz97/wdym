package xyz.danizz.wdym.domain.usecase

import xyz.danizz.wdym.domain.model.Player

class JoinPlayerUseCase(
    private val findGameByCodeUseCase: FindGameByCodeUseCase
) {
    operator fun invoke(gameCode: Int, player: Player) {
        val game = findGameByCodeUseCase(gameCode)
        game?.addPlayer(player) ?: throw IllegalStateException("Game should exist here")
    }
}