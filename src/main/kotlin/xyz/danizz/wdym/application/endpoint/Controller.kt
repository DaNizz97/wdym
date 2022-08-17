package xyz.danizz.wdym.application.endpoint

import org.springframework.web.bind.annotation.*
import xyz.danizz.wdym.application.config.ConfigProperties
import xyz.danizz.wdym.data.repository.MemeImagePathRepository
import xyz.danizz.wdym.domain.model.*
import xyz.danizz.wdym.domain.usecase.CreateGameUseCase
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@CrossOrigin("*")
class Controller(
    private val memeImagePathRepository: MemeImagePathRepository,
    private val properties: ConfigProperties,
    private val createGameUseCase: CreateGameUseCase
) {

    @GetMapping
    fun popa(): ByteArray {
        val path = Paths.get(properties.directory)
            .resolve(memeImagePathRepository.findById("62d4be2b9bdaef5775a15abe").get().path)
        return Files.readAllBytes(path)
    }

    @PostMapping(value = ["/game/create"])
    fun createGame(@RequestBody gameRequest: GameRequestModel): GameResponseModel {
        val type = GameType.valueOf(gameRequest.type.uppercase())
        val game = createGameUseCase(gameRequest.name, type)
        return game.toResponseModel()
    }
}

data class GameRequestModel(val name: String, val type: String)

data class GameResponseModel(val code: Int, val name: String, val type: GameType, val players: List<Player>)

fun Game.toResponseModel(): GameResponseModel {
    val type: GameType = when (this) {
        is MemesBasedGame -> GameType.MEMES
        is SituationBasedGame -> GameType.SITUATION
    }
    return GameResponseModel(this.code, this.name, type, this.players)
}