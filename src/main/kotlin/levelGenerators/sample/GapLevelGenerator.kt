package levelGenerators.sample

import engine.core.MarioLevelGenerator
import engine.core.MarioLevelModel
import engine.core.MarioTimer
import kotlin.random.Random

class GapLevelGenerator: MarioLevelGenerator {
    override fun getGeneratedLevel(model: MarioLevelModel, timer: MarioTimer): String {
        val rng = Random.Default
        model.clearMap()

        var currentX = 0

        while (currentX < model.width) {
            val groundLength = rng.nextInt(3, 8)
            val gapLength = rng.nextInt(2, 10)
            for (x in currentX until currentX + groundLength) {
                model.setBlock(x, model.height - 1, MarioLevelModel.GROUND)

                if (x >= model.width) break
            }
            currentX += groundLength + gapLength
        }

        return model.map
    }

    override fun getGeneratorName(): String {
         return this.javaClass.simpleName;
    }
}