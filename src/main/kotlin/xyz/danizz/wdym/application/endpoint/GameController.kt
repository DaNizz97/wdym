package xyz.danizz.wdym.application.endpoint

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import xyz.danizz.wdym.application.config.ConfigProperties
import xyz.danizz.wdym.data.repository.MemeImagePathRepository
import xyz.danizz.wdym.domain.model.*
import xyz.danizz.wdym.domain.usecase.CreateGameUseCase
import xyz.danizz.wdym.domain.usecase.FindGameByCodeUseCase
import xyz.danizz.wdym.domain.usecase.JoinPlayerUseCase
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@CrossOrigin("*")
class GameController(
    private val memeImagePathRepository: MemeImagePathRepository,
    private val properties: ConfigProperties,
    private val createGameUseCase: CreateGameUseCase,
    private val findGameByCodeUseCase: FindGameByCodeUseCase,
    private val joinPlayerUseCase: JoinPlayerUseCase
) {

    @GetMapping("/image-test")
    fun popa(): ByteArray {
        val path = Paths.get(properties.directory)
            .resolve(memeImagePathRepository.findById("62d4be2b9bdaef5775a15abe").get().path)
        return Files.readAllBytes(path)
    }

    @GetMapping("/game/{code}")
    fun findGameByCode(
        @PathVariable code: Int
    ): GameResponseModel {
        return findGameByCodeUseCase(code)?.toResponseModel() ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "game not found"
        )
    }

    @PostMapping(value = ["/game/create"])
    fun createGame(@RequestBody gameRequest: GameRequestModel): GameResponseModel {
        val type = GameType.valueOf(gameRequest.type.uppercase())
        val game = createGameUseCase(gameRequest.name, type)
        return game.toResponseModel()
    }

    @PostMapping("/game/join")
    fun joinGame(@RequestBody joinRequest: JoinRequestModel) {
        joinPlayerUseCase(joinRequest.gameCode, joinRequest.getPlayer())
    }
}

private fun JoinRequestModel.getPlayer(): Player {
    return Player(this.playerName)
}

data class JoinRequestModel(val playerName: String, val gameCode: Int)

data class GameRequestModel(val name: String, val type: String)

data class GameResponseModel(val code: Int, val name: String, val type: GameType, val players: List<Player>)

fun Game.toResponseModel(): GameResponseModel {
    val type: GameType = when (this) {
        is MemesBasedGame -> GameType.MEMES
        is SituationBasedGame -> GameType.SITUATION
    }
    return GameResponseModel(this.code, this.name, type, this.players)
}