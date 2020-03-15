package metrics;

import engine.core.MarioEvent;
import engine.core.MarioGame;
import engine.core.MarioResult;
import engine.helper.EventType;
import org.jetbrains.annotations.NotNull;

class JumpMetricJava extends AbstractMetric {
    @NotNull
    @Override
    public String getName() {
        return "jump";
    }

    @NotNull
    @Override
    public String getValue(@NotNull String level) {
        MarioResult results = runBaumgartenAgent(level);

        int jumpCount = 0;
        for (MarioEvent gameEvent: results.getGameEvents()) {
            if (gameEvent.getEventType() == EventType.JUMP.getValue()) ++jumpCount;
        }
        return Integer.toString(jumpCount);
    }

    private MarioResult runBaumgartenAgent(String level) {
        MarioGame game = new MarioGame();
        return game.runGame(
            new agents.robinBaumgarten.Agent(),
            level,
            200,
            0
        );
    }
}