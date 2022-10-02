package xyz.danizz.wdym.domain.usecase

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.jupiter.MockitoExtension
import xyz.danizz.wdym.domain.model.GameType
import xyz.danizz.wdym.domain.model.MemesBasedGame
import xyz.danizz.wdym.domain.model.SituationBasedGame
import xyz.danizz.wdym.domain.repository.GameRepository

@ExtendWith(MockitoExtension::class)
internal class CreateGameUseCaseTest {

    lateinit var createGameUseCase: CreateGameUseCase
    lateinit var getAvailableCodeUseCase: GetAvailableCodeUseCase

    @Mock
    private lateinit var gameRepository: GameRepository

    @BeforeEach
    internal fun setUp() {
        getAvailableCodeUseCase = GetAvailableCodeUseCase(gameRepository)
        createGameUseCase = CreateGameUseCase(gameRepository, getAvailableCodeUseCase)
    }

    @Test
    internal fun testThatkek() {
        val games = listOf(MemesBasedGame("popa", 111111))
        doReturn(games).`when`(gameRepository).getAll()
        val code = getAvailableCodeUseCase()
        assertTrue(code in 100_000..999_999)
        assertNotEquals(code, games[0].code)
    }

    @Test
    internal fun testThatCorrectGameCreated() {
        val gameName = "Test"
        val game = createGameUseCase(gameName, GameType.SITUATION)
        assertEquals(gameName, game.name)
        assertEquals(0, game.players.size)
        assertInstanceOf(SituationBasedGame::class.java, game)
    }

    @Test
    internal fun `test that game code is valid`() {
        val game = createGameUseCase("Test", GameType.SITUATION)
        assertTrue(game.code in 100_000..999_999)
    }
}