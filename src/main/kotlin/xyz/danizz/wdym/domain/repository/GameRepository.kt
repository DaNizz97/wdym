package xyz.danizz.wdym.domain.repository

import xyz.danizz.wdym.domain.model.Game

interface GameRepository {
    fun put(newGame: Game)
    fun getByCode(code: Int): Game?
}