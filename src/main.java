
import agents.doNothing.DoNothingAgent;
import agents.human.HumanAgent;
import engine.core.MarioGame;


public class main {
    public static void main(String[] args) {
	MarioGame game = new MarioGame();
	game.pause = false;
	game.runVisuals(new HumanAgent(), 
		"................................\n" +
		"................................\n" +
		"................................\n" +
		"................................\n" +
		"................................\n" +
		"................................\n" +
		"................................\n" +
		"................................\n" +
		"..............o...o...........O.\n" +
		".............SLC@CBB12........T.\n" +
		"...%%%%%...............(>.....W.\n" +
		"....|||........<>......[].....W.\n" +
		"M...|||........[]......[]...k.W.\n" +
		"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
		"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n", 30, 30, 2);
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
