
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import agents.doNothing.DoNothingAgent;
import agents.human.HumanAgent;
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
	game.runVisuals(new HumanAgent(), getLevel("levels/mario-1-1.txt"), 10, 30, 2);
//	game.runNoVisuals(new DoNothingAgent(), 
//		"................................\n" +
//		"................................\n" +
//		"................................\n" +
//		"................................\n" +
//		"................................\n" +
//		"................................\n" +
//		"................................\n" +
//		"................................\n" +
//		"..............o...o.............\n" +
//		".............BBC@CBB............\n" +
//		"................................\n" +
//		"................................\n" +
//		"M..............................E\n" +
//		"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
//		"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n", 2);
	System.out.println("Hello");
    }
}
