package xyz.danizz.wdym.data.repository

import org.springframework.stereotype.Component
import xyz.danizz.wdym.domain.model.Game
import xyz.danizz.wdym.domain.repository.GameRepository

@Component
class GameRepositoryImpl : GameRepository {

    private val storage: MutableList<Game>

    init {
        storage = ArrayList()
    }

    override fun put(newGame: Game) {
        storage.add(newGame)
    }

    override fun getByCode(code: Int): Game? {
        return storage.find { game -> game.code == code }
    }
}