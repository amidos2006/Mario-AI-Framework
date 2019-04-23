
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import agents.aStar.AStarAgent;
import agents.doNothing.DoNothingAgent;
import engine.core.MarioGame;


public class main {
    public static String getLevel(String filepath) {
	String content = "";
	try {
	    content = new String(Files.readAllBytes(Paths.get(filepath)));
	} catch (IOException e) {

	}
	return content;
    }
    
    public static void main(String[] args) {
	MarioGame game = new MarioGame();
	game.pause = false;
//	game.playGame(getLevel("levels/mario-1-1.txt"), 200);
	game.runGame(new AStarAgent(), getLevel("levels/mario-1-1.txt"), 200, true);
	System.out.println("Hello");
    }
}
