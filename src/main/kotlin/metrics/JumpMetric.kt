package metrics

import engine.core.MarioGame
import engine.core.MarioResult
import engine.helper.EventType

class JumpMetric: AbstractMetric() {
    override val name = "jumps"

    override fun getValue(level: String): String {
        val results = runBaumgartenAgent(level)

        val jumpCount = results.gameEvents.filter { it.eventType == EventType.JUMP.value }.count()
        return jumpCount.toString()
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