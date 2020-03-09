package cz.cuni.gamedev.nail123.mariolevelgeneration

import PlayLevel
import cz.cuni.gamedev.nail123.mariolevelgeneration.levelGenerators.sample.GapLevelGenerator
import engine.core.MarioGame
import engine.core.MarioLevelGenerator
import engine.core.MarioLevelModel
import engine.core.MarioTimer

fun main() {
    val game = MarioGame()
    val generator: MarioLevelGenerator = GapLevelGenerator()

    val level = generator.getGeneratedLevel(
            MarioLevelModel(150, 16),
            MarioTimer(5 * 60 * 60 * 1000)
    )
//    val level = PlayLevel.getLevel("levels/original/lvl-1.txt")

    val results = game.runGame(
            agents.robinBaumgarten.Agent(),
            level,
            200,
            0,
            true
    )

    PlayLevel.printResults(results)
}