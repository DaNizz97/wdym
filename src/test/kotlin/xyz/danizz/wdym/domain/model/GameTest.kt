package xyz.danizz.wdym.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class GameTest {
    @Test
    internal fun testThatname() {
        val game = MemesBasedGame("name", 123)
        val john = Player("John")
        val vasya = Player("Vasya")
        game.addPlayer(john)
        game.addPlayer(vasya)
        assertEquals(game.players.size, 2)
        game.removePlayer(john)
        assertEquals(game.players.size, 1)
    }

}