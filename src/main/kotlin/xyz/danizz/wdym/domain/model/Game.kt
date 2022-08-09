package xyz.danizz.wdym.domain.model

sealed class Game {
    abstract val name: String
    abstract val code: Int
    private val _players: MutableList<Player> = mutableListOf()
    val players: List<Player> = _players

    fun addPlayer(player: Player) = _players.add(player)
    fun removePlayer(player: Player) = _players.remove(player)
}

data class MemesBasedGame(override val name: String, override val code: Int) : Game()
data class SituationBasedGame(override val name: String, override val code: Int) : Game()
