package cz.cuni.gamedev.nail123.mariolevelgeneration.levelGenerators.notch

import engine.core.MarioLevelGenerator
import engine.core.MarioLevelModel
import engine.core.MarioTimer
import java.util.*

class LevelGenerator: MarioLevelGenerator {
    private val GROUND_PADDING = 5
    private val GROUND_LENGTH = 8
    private val GAP_LENGTH = 6
    private val GAP_PROB = 0.1f
    private val PIPE_PROB = 0.75f
    private val GROUND_PIPE_LENGTH = 10
    private val PIPE_HEIGHT = 5
    private val COLLECTIBLE_PROB = 0.75f
    private val GROUND_COLLECTIBLE_LENGTH = 6
    private val GROUND_ENEMY_LENGTH = 2

    private var rnd: Random? = null

    private fun placePipe(model: MarioLevelModel, x: Int, y: Int, height: Int) {
        var pipeType = MarioLevelModel.PIPE
        if (rnd!!.nextDouble() < 0.2) {
            pipeType = MarioLevelModel.PIPE_FLOWER
        }
        model.setRectangle(x, y - height + 1, 2, height, pipeType)
    }

    private fun placeInterestingArrangement(model: MarioLevelModel, x: Int, y: Int, width: Int) {
        for (i in 0 until width / 2) {
            val type = MarioLevelModel.getBumpableTiles()[rnd!!.nextInt(MarioLevelModel.getBumpableTiles().size)]
            model.setBlock(x + i, y, type)
            model.setBlock(x + width - 1 - i, y, type)
        }
        if (width % 2 == 1 && rnd!!.nextDouble() < 0.25) {
            val type = MarioLevelModel.getBumpableTiles()[rnd!!.nextInt(MarioLevelModel.getBumpableTiles().size)]
            model.setBlock(x + width / 2, y, type)
        }
        if (y > 4 && rnd!!.nextDouble() < 0.25) {
            placeInterestingArrangement(model, x + width / 4, y - 3 - rnd!!.nextInt(3), width / 2)
        }
    }

    private fun placeEnemy(model: MarioLevelModel, x1: Int, x2: Int, y: Int) {
        val winged = rnd!!.nextDouble() < 0.1
        var enemyType = MarioLevelModel.getEnemyCharacters(false)[rnd!!.nextInt(MarioLevelModel.getEnemyCharacters(false).size)]
        enemyType = MarioLevelModel.getWingedEnemyVersion(enemyType, winged)
        val xStart = x1 + rnd!!.nextInt(x2 - x1)
        var length = 1 + rnd!!.nextInt(3)
        if (length > x2 - x1 - 1) {
            length = x2 - x1 - 1
        }
        for (i in 0 until length) {
            if (model.getBlock(xStart + i, y) == MarioLevelModel.EMPTY) model.setBlock(xStart + i, y, enemyType)
        }
    }

    override fun getGeneratedLevel(model: MarioLevelModel, timer: MarioTimer?): String? {
        rnd = Random()
        model.clearMap()
        val groundArea = ArrayList<Int>()
        groundArea.add(0)
        var groundLength = GROUND_LENGTH / 2 + rnd!!.nextInt(GROUND_LENGTH / 2)
        var gapLength = 0
        //add ground
        for (x in 0 until model.width) {
            if (groundLength > 0 || gapLength == 0 || x < GROUND_PADDING || x > model.width - 1 - GROUND_PADDING) {
                model.setBlock(x, model.height - 1, MarioLevelModel.GROUND)
                model.setBlock(x, model.height - 2, MarioLevelModel.GROUND)
                groundLength -= 1
                if (groundLength <= 0 && rnd!!.nextDouble() < GAP_PROB) {
                    gapLength = GAP_LENGTH / 2 + rnd!!.nextInt(GAP_LENGTH / 2)
                }
                if (groundArea.size % 2 == 0) {
                    groundArea.add(x)
                }
            } else {
                gapLength -= 1
                if (gapLength <= 0) {
                    groundLength = GROUND_LENGTH / 2 + rnd!!.nextInt(GROUND_LENGTH / 2)
                }
                if (groundArea.size % 2 == 1) {
                    groundArea.add(x)
                }
            }
        }
        groundArea.add(model.width - 1)
        //add pipes
        val newAreas = ArrayList<Int>()
        for (i in 0 until groundArea.size / 2) {
            groundLength = groundArea[2 * i + 1] - groundArea[2 * i]
            if (groundLength > GROUND_PIPE_LENGTH && rnd!!.nextDouble() < PIPE_PROB) {
                val x = groundArea[2 * i] + rnd!!.nextInt(groundLength / 4) + 3
                placePipe(model, x, model.height - 3, rnd!!.nextInt(PIPE_HEIGHT * 2 / 3) + PIPE_HEIGHT / 3)
                newAreas.add(groundArea[2 * i])
                newAreas.add(x - 1)
                newAreas.add(x + 2)
                newAreas.add(groundArea[2 * i + 1])
            }
        }
        //add interesting patterns
        groundArea.clear()
        for (i in 0 until newAreas.size / 2) {
            groundLength = newAreas[2 * i + 1] - newAreas[2 * i]
            groundArea.add(newAreas[2 * i])
            groundArea.add(model.height - 3)
            groundArea.add(newAreas[2 * i + 1])
            groundArea.add(model.height - 3)
            if (groundLength > GROUND_COLLECTIBLE_LENGTH && rnd!!.nextDouble() < COLLECTIBLE_PROB) {
                val x = newAreas[2 * i] + rnd!!.nextInt(groundLength / 3) + 1
                val y = model.height - 5 - rnd!!.nextInt(3)
                val length = 1 + rnd!!.nextInt(groundLength / 3)
                placeInterestingArrangement(model, x, y, length)
                groundArea.add(x + 1)
                groundArea.add(y - 1)
                groundArea.add(x + length - 1)
                groundArea.add(y - 1)
            }
        }
        //add enemies
        for (i in 1 until groundArea.size / 4) {
            groundLength = groundArea[4 * i + 2] - groundArea[4 * i]
            if (groundLength > GROUND_ENEMY_LENGTH) {
                placeEnemy(model, groundArea[4 * i], groundArea[4 * i + 2], groundArea[4 * i + 1])
            }
        }
        model.setBlock(1, model.height - 3, MarioLevelModel.MARIO_START)
        model.setBlock(model.width - 2, model.height - 3, MarioLevelModel.PYRAMID_BLOCK)
        model.setBlock(model.width - 2, model.height - 4, MarioLevelModel.MARIO_EXIT)
        return model.map
    }

    override fun getGeneratorName(): String? {
        return "NotchLevelGenerator"
    }

}