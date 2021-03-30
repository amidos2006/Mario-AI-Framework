import engine.core.MarioGame
import engine.core.MarioLevelGenerator
import engine.core.MarioLevelModel
import engine.core.MarioTimer
import levelGenerators.sample.GapLevelGenerator

fun main() {
    val game = MarioGame()
    val generator: MarioLevelGenerator = GapLevelGenerator()

    val level = generator.getGeneratedLevel(
            MarioLevelModel(150, 16),
            MarioTimer(5 * 60 * 60 * 1000)
    )
    println("GENERATED LEVEL:")
    println(level)

    // You can use this to get a level from saved file
//    val level = PlayLevel.getLevel("levels/original/lvl-1.txt")

    val results = game.runGame(
            // HUMAN control (S = jump, A = run/shoot)
//            agents.human.Agent(),

            // A* agent
            agents.robinBaumgarten.Agent(),

            level,
            200,
            0,
            true
    )

    PlayLevel.printResults(results)
}