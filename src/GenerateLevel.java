import engine.core.MarioGame;
import engine.core.MarioLevelGenerator;
import engine.core.MarioTimer;

public class GenerateLevel {
    public static void main(String[] args) {
	MarioLevelGenerator generator = new levelGenerators.linear.LevelGenerator();
	String level = generator.getGeneratedLevel(150, 16, new MarioTimer(5*60*60*1000));
	MarioGame game = new MarioGame();
	game.runGame(new agents.robinBaumgarten.Agent(), level, 200, 0, true);
    }
}
