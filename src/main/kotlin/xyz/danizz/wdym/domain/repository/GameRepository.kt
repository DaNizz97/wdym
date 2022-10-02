package xyz.danizz.wdym.domain.repository

import xyz.danizz.wdym.domain.model.Game

interface GameRepository {
    fun put(newGame: Game): Boolean
    fun getByCode(code: Int): Game?
    fun getAll(): List<Game>
}