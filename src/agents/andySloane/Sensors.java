package agents.andySloane;

import engine.core.MarioForwardModel;
import engine.core.MarioGame;
import engine.helper.SpriteType;

public class Sensors {
    private String[][] asciiScene;
    public int[][] levelScene;
    public int[][] enemiesScene;
    public int fireballsOnScreen;

    public void updateReadings(MarioForwardModel model) {
	levelScene = model.getMarioSceneObservation();
	enemiesScene = model.getMarioEnemiesObservation();

	asciiScene = new String[MarioGame.tileWidth][MarioGame.tileHeight];

	fireballsOnScreen = 0;
	for (int x = 0; x < levelScene.length; ++x)
	    for (int y = 0; y < levelScene[0].length; ++y)
		asciiScene[x][y] = asciiLevel(levelScene[x][y]);
	for (int x = 0; x < enemiesScene.length; ++x)
	    for (int y = 0; y < enemiesScene[0].length; ++y) {
		int enemy = enemiesScene[x][y];
		if (enemy == SpriteType.NONE.getValue())
		    continue;
		if (enemy == SpriteType.FIREBALL.getValue())
		    fireballsOnScreen++;
		asciiScene[x][y] = asciiEnemy(enemy);
	    }
    }

    public int[] getMarioPosition() {
	return new int[] {8, 8};
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (String[] sceneRow : asciiScene) {
	    for (String square : sceneRow)
		sb.append(square + " ");
	    sb.append('\n');
	}
	return sb.toString();
    }

    public final static int EMPTY = 0;
    public final static int COIN = 31;
    public final static int SOLID = 17;
    public final static int PLATFORM = 59;
    public final static int QUESTIONMARK_BOX = 24;
    public final static int BRICK = 23;

    private String asciiLevel(int levelSquare) {
	switch (levelSquare) {
	case EMPTY:
	    return " ";
	case COIN:
	    return "O";
	case SOLID:
	    return "X";
	case PLATFORM:
	    return "-";
	case BRICK:
	    return "B";
	case QUESTIONMARK_BOX:
	    return "?";
	default:
	    return "" + levelSquare;
	}
    }

    private String asciiEnemy(int enemySquare) {
	if(enemySquare == SpriteType.GOOMBA.getValue()) {
	    return "G";
	}
	if(enemySquare == SpriteType.RED_KOOPA.getValue() || enemySquare == SpriteType.GREEN_KOOPA.getValue()) {
	    return "n";
	}
	if(enemySquare == SpriteType.GOOMBA_WINGED.getValue() || enemySquare == SpriteType.RED_KOOPA_WINGED.getValue() || 
		enemySquare == SpriteType.GREEN_KOOPA_WINGED.getValue()) {
	    return "w";
	}
	if(enemySquare == SpriteType.SHELL.getValue()) {
	    return "D";
	}
	if(enemySquare == SpriteType.SPIKY.getValue()) {
	    return "^";
	}
	if(enemySquare == SpriteType.SPIKY_WINGED.getValue()) {
	    return "W";
	}
	if(enemySquare == SpriteType.BULLET_BILL.getValue()) {
	    return "<";
	}
	if(enemySquare == SpriteType.ENEMY_FLOWER.getValue()) {
	    return "V";
	}
	if(enemySquare == SpriteType.FIRE_FLOWER.getValue()) {
	    return "F";
	}
	if(enemySquare == SpriteType.FIREBALL.getValue()) {
	    return "*";
	}
	return "" + enemySquare;
    }
}
