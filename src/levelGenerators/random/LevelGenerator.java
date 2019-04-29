package levelGenerators.random;

import java.util.Random;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

public class LevelGenerator implements MarioLevelGenerator{
    @Override
    public String getGeneratedLevel(int levelWidth, int levelHeight, MarioTimer timer) {
	Random random = new Random();
	char[][] map = new char[levelWidth][levelHeight];
	for(int x=0; x<map.length; x++) {
	    for(int y=0; y<map[x].length; y++) {
		map[x][y] = '-';
		if(y > 13) {
		    if(random.nextDouble() < 0.4) {
			map[x][y] = MarioLevelModel.GROUND;
		    }
		}
		else if(y > 10){
		    if(random.nextDouble() < 0.1) {
			map[x][y] = MarioLevelModel.PYRAMID_BLOCK;
		    }
		}
		else if(y > 3) {
		    if(random.nextDouble() < 0.05) {
			map[x][y] = MarioLevelModel.getCollectablesTiles()[random.nextInt(MarioLevelModel.getCollectablesTiles().length)];
		    }
		}
	    }
	}
	for(int i=0; i<3; i++) {
	    map[i][14] = MarioLevelModel.GROUND;
	    map[i][15] = MarioLevelModel.GROUND;
	    map[levelWidth - i - 1][14] = MarioLevelModel.GROUND;
	    map[levelWidth - i - 1][15] = MarioLevelModel.GROUND;
	}
	map[1][13] = MarioLevelModel.MARIO_START;
	map[levelWidth - 2][13] = MarioLevelModel.PYRAMID_BLOCK;
	map[levelWidth - 2][12] = MarioLevelModel.MARIO_EXIT;
	return MarioLevelModel.createStringMap(map);
    }

    @Override
    public String getGeneratorName() {
	return "RandomLevelGenerator";
    }

}
