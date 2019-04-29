package levelGenerators.random;

import java.util.Random;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

public class LevelGenerator implements MarioLevelGenerator{
    private final int GROUND_Y_LOCATION = 13;
    private final float GROUND_PROB = 0.4f;
    private final int OBSTACLES_LOCATION = 10;
    private final float OBSTACLES_PROB = 0.1f;
    private final int COLLECTIBLE_LOCATION = 3;
    private final float COLLECTIBLE_PROB = 0.05f;
    private final float ENMEY_PROB = 0.1f;
    private final int FLOOR_PADDING = 3;
    
    @Override
    public String getGeneratedLevel(int levelWidth, int levelHeight, MarioTimer timer) {
	Random random = new Random();
	char[][] map = new char[levelWidth][levelHeight];
	for(int x=0; x<map.length; x++) {
	    for(int y=0; y<map[x].length; y++) {
		map[x][y] = '-';
		if(y > GROUND_Y_LOCATION) {
		    if(random.nextDouble() < GROUND_PROB) {
			map[x][y] = MarioLevelModel.GROUND;
		    }
		}
		else if(y > OBSTACLES_LOCATION){
		    if(random.nextDouble() < OBSTACLES_PROB) {
			map[x][y] = MarioLevelModel.PYRAMID_BLOCK;
		    }
		    else if(random.nextDouble() < ENMEY_PROB) {
			map[x][y] = MarioLevelModel.getEnemyCharacters()[random.nextInt(MarioLevelModel.getEnemyCharacters().length)];
		    }
		}
		else if(y > COLLECTIBLE_LOCATION) {
		    if(random.nextDouble() < COLLECTIBLE_PROB) {
			map[x][y] = MarioLevelModel.getCollectablesTiles()[random.nextInt(MarioLevelModel.getCollectablesTiles().length)];
		    }
		}
	    }
	}
	for(int i=0; i<FLOOR_PADDING; i++) {
	    map[i][14] = MarioLevelModel.GROUND;
	    map[i][15] = MarioLevelModel.GROUND;
	    map[levelWidth - i - 1][14] = MarioLevelModel.GROUND;
	    map[levelWidth - i - 1][15] = MarioLevelModel.GROUND;
	}
	map[FLOOR_PADDING/2][13] = MarioLevelModel.MARIO_START;
	map[levelWidth - 1 - FLOOR_PADDING/2][13] = MarioLevelModel.PYRAMID_BLOCK;
	map[levelWidth - 1 - FLOOR_PADDING/2][12] = MarioLevelModel.MARIO_EXIT;
	return MarioLevelModel.createStringMap(map);
    }

    @Override
    public String getGeneratorName() {
	return "RandomLevelGenerator";
    }

}
