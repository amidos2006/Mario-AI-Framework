import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import engine.core.MarioGame;
import engine.core.MarioResult;

public class PlayLevel {
    public static void printResults(MarioResult result) {
        System.out.println("****************************************************************");
        System.out.println("Game Status: " + result.getGameStatus().toString() +
                " Percentage Completion: " + result.getCompletionPercentage());
        System.out.println("Lives: " + result.getCurrentLives() + " Coins: " + result.getCurrentCoins() +
                " Remaining Time: " + (int) Math.ceil(result.getRemainingTime() / 1000f));
        System.out.println("Mario State: " + result.getMarioMode() +
                " (Mushrooms: " + result.getNumCollectedMushrooms() + " Fire Flowers: " + result.getNumCollectedFireflower() + ")");
        System.out.println("Total Kills: " + result.getKillsTotal() + " (Stomps: " + result.getKillsByStomp() +
                " Fireballs: " + result.getKillsByFire() + " Shells: " + result.getKillsByShell() +
                " Falls: " + result.getKillsByFall() + ")");
        System.out.println("Bricks: " + result.getNumDestroyedBricks() + " Jumps: " + result.getNumJumps() +
                " Max X Jump: " + result.getMaxXJump() + " Max Air Time: " + result.getMaxJumpAirTime());
        System.out.println("****************************************************************");
    }

    public static String getLevel(String filepath) {
      try (Reader r = new InputStreamReader(PlayLevel.class.getResourceAsStream(filepath))) {
        StringWriter writer = new StringWriter();
        char[] buf = new char[1024];
        int read;
        while ((read = r.read(buf)) != -1) {
          writer.write(buf, 0, read);
        }
        return writer.toString();
      } catch (IOException e) {
        return "";
      }
    }

    public static void main(String[] args) {
      MarioGame game = new MarioGame();
      // printResults(game.playGame(getLevel("/original/lvl-1.txt"), 200, 0));
      printResults(game.runGame(new agents.robinBaumgarten.Agent(), getLevel("/original/lvl-1.txt"), 20, 0, true));
    }
}
