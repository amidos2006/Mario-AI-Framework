package metrics

import engine.core.MarioGame
import engine.core.MarioResult

/**
 * A linearity metric, measured by a trajectory of the Baumgarten agent.
 *
 * It is output in the format of "X:Y" coordinates where Mario was on ground, separated by dashes.
 */
class LinearityMetric: AbstractMetric() {
    override val name = "linearity"

    override fun getValue(level: String): String {
        val sb = StringBuilder()

        val results = runBaumgartenAgent(level)

        // Each time-step, add "X:Y" coordinate pair if mario was on ground
        results.agentEvents
            .filter { it.marioOnGround }
            .forEach { event ->
                if (sb.isNotEmpty()) sb.append("_")
                sb.append(event.marioX)
                sb.append(':')
                sb.append(event.marioY)
            }

        return sb.toString()
    }

    private fun runBaumgartenAgent(level: String): MarioResult {
        val game = MarioGame()
        return game.runGame(
            agents.robinBaumgarten.Agent(),
            level,
            200,
            0
        )
    }
}