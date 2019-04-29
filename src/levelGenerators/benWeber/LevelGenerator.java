package levelGenerators.benWeber;

import java.util.ArrayList;
import java.util.Random;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

public class LevelGenerator implements MarioLevelGenerator{
    private int maxGaps;
    private int maxTurtles;
    private int maxCoinBlocks;

    private double CHANCE_BLOCK_POWER_UP = 0.1;
    private double CHANCE_BLOCK_COIN = 0.3;
    private double CHANCE_BLOCK_ENEMY = 0.2;
    private double CHANCE_WINGED = 0.5;
    private double CHANCE_COIN = 0.2;
    private double COIN_HEIGHT = 5;
    private double CHANCE_PLATFORM = 0.1;
    private double CHANCE_END_PLATFORM = 0.1;
    private int PLATFORM_HEIGHT = 4;
    private double CHANCE_ENEMY = 0.1;
    private double CHANCE_PIPE = 0.1;
    private int PIPE_MIN_HEIGHT = 2;
    private double PIPE_HEIGHT = 3.0;
    private int minX = 5;
    private double CHANCE_HILL = 0.1;
    private double CHANCE_END_HILL = 0.3;
    private double CHANCE_HILL_ENEMY = 0.3;
    private double HILL_HEIGHT = 4;
    private int GAP_LENGTH = 5;
    private double CHANGE_GAP = 0.1;
    private double CHANGE_HILL_CHANGE = 0.1;
    private double GAP_OFFSET = -5;
    private double GAP_RANGE = 10;
    private int GROUND_MAX_HEIGHT = 5;

    // controls the fun
    Random rand;
    char[][] map;

    // constraints
    int gapCount = 0;
    int turtleCount = 0;
    int coinBlockCount = 0;

    int xExit = 0;
    int yExit = 0;
    
    public LevelGenerator() {
	this(10, 7, 10);
    }
    
    public LevelGenerator(int maxGaps, int maxTurtles, int maxCoinBlocks) {
	this.maxGaps = maxGaps;
	this.maxTurtles = maxTurtles;
	this.maxCoinBlocks = maxCoinBlocks;
    }

    private char getBlock(int x, int y) {
	int currentX = x;
	int currentY = y;
	if(x < 0) currentX = 0;
	if(y < 0) currentY = 0;
	if(x > this.map.length - 1) currentX = this.map.length - 1;
	if(y > this.map[0].length - 1) currentY = this.map[0].length - 1;
	return this.map[currentX][currentY];
    }
    
    private void setBlock(int x, int y, char value) {
	if(x < 0 || y < 0 || x > this.map.length - 1 || y > this.map[0].length - 1) return;
	this.map[x][y] = value;
    }
    
    private void placeBlock(int x, int y) {
	// choose block type
	if (rand.nextDouble() < CHANCE_BLOCK_POWER_UP) {
	    this.setBlock(x, y, MarioLevelModel.SPECIAL_BRICK);
	} else if (rand.nextDouble() < CHANCE_BLOCK_COIN && coinBlockCount < this.maxCoinBlocks) {
	    this.setBlock(x, y, MarioLevelModel.COIN_BRICK);
	    coinBlockCount++;
	} else {
	    this.setBlock(x, y, MarioLevelModel.NORMAL_BRICK);
	}

	// place enemies
	if (rand.nextDouble() < CHANCE_BLOCK_ENEMY) {
	    char t = MarioLevelModel.getEnemyCharacters(false)[this.rand.nextInt(MarioLevelModel.getEnemyCharacters(false).length)];
	    // turtle constraint
	    if (t == MarioLevelModel.GREEN_KOOPA || t == MarioLevelModel.RED_KOOPA) {
		if (turtleCount < this.maxTurtles) {
		    turtleCount++;
		} else {
		    t = MarioLevelModel.GOOMBA;
		}
	    }
	    boolean winged = rand.nextDouble() < CHANCE_WINGED;
	    this.setBlock(x, y-1, MarioLevelModel.getWingedEnemyVersion(t, winged));
	}
    }

    private void placePipe(int x, int y, int height) {
	for (int i = 1; i <= height; i++) {
	    this.setBlock(x, y-i, MarioLevelModel.PIPE);
	    this.setBlock(x+1, y-i, MarioLevelModel.PIPE);
	}
    }

    private void setGroundHeight(int x, int y, int lastY, int nextY) {
	int mapHeight = this.map[0].length;
	for (int i = y + 1; i < mapHeight; i++) {
	    this.setBlock(x, i, MarioLevelModel.PLATFORM_BACKGROUND);
	}

	if (y < lastY) {
	    this.setBlock(x, y, MarioLevelModel.PLATFORM);
	    for (int i = y + 1; i <= lastY; i++) {
		this.setBlock(x, i, MarioLevelModel.PLATFORM_BACKGROUND);
	    }
	} else if (y < nextY) {
	    this.setBlock(x, y, MarioLevelModel.PLATFORM);
	    for (int i = y + 1; i <= nextY; i++) {
		this.setBlock(x, i, MarioLevelModel.PLATFORM_BACKGROUND);
	    }
	} else {
	    this.setBlock(x, y, MarioLevelModel.PLATFORM);
	}
	
	// place the exit
	if (x == (this.map.length - 5)) {
	    this.yExit = y;
	}
    }

    public String getGeneratedLevel(int levelWidth, int levelHeight, MarioTimer timer) {
	this.rand = new Random();
	this.map = MarioLevelModel.createEmptyMap(levelWidth, levelHeight);

	ArrayList<Integer> ground = new ArrayList<Integer>();

	// used to place the ground
	int lastY = GROUND_MAX_HEIGHT + (int) (rand.nextDouble() * (levelHeight - 1 - GROUND_MAX_HEIGHT));
	int y = lastY;
	int nextY = y;
	boolean justChanged = false;
	int length = 0;
	int landHeight = levelHeight - 1;

	// place the ground
	for (int x = 0; x < levelWidth; x++) {

	    // need more ground
	    if (length > GAP_LENGTH && y >= levelHeight) {
		nextY = landHeight;
		justChanged = true;
		length = 1;
	    }
	    // adjust ground level
	    else if (x > minX && rand.nextDouble() < CHANGE_HILL_CHANGE && !justChanged) {
		nextY += (int) (GAP_OFFSET + GAP_RANGE * rand.nextDouble());
		nextY = Math.min(levelHeight - 2, nextY);
		nextY = Math.max(5, nextY);
		justChanged = true;
		length = 1;
	    }
	    // add a gap
	    else if (x > minX && y < levelHeight && rand.nextDouble() < CHANGE_GAP && !justChanged
		    && gapCount < this.maxGaps) {
		landHeight = Math.min(levelHeight - 1, lastY);
		nextY = levelHeight;
		justChanged = true;
		length = 1;
		gapCount++;
	    } else {
		length++;
		justChanged = false;
	    }

	    setGroundHeight(x, y, lastY, nextY);
	    ground.add(y);

	    lastY = y;
	    y = nextY;
	}

	// non colliding hills
	int x = 0;
	y = levelHeight;
	for (Integer h : ground) {
	    if (y == levelHeight) {
		if (x > 10 && rand.nextDouble() < CHANCE_HILL) {
		    y = (int) (HILL_HEIGHT + rand.nextDouble() * (h - HILL_HEIGHT));
		    this.setBlock(x, y, MarioLevelModel.PLATFORM);
		    for (int i = y + 1; i < h; i++) {
			this.setBlock(x, i, MarioLevelModel.PLATFORM_BACKGROUND);
		    }
		}
	    } else {
		// end if hitting a wall
		if (y >= h) {
		    y = levelHeight;
		} else if (rand.nextDouble() < CHANCE_END_HILL) {
		    this.setBlock(x, y, MarioLevelModel.PLATFORM);
		    for (int i = y + 1; i < h; i++) {
			this.setBlock(x, i, MarioLevelModel.PLATFORM_BACKGROUND);
		    }
		    
		    y = levelHeight;
		} else {
		    this.setBlock(x, y, MarioLevelModel.PLATFORM);
		    for (int i = y + 1; i < h; i++) {
			this.setBlock(x, i, MarioLevelModel.PLATFORM_BACKGROUND);
		    }

		    if (rand.nextDouble() < CHANCE_HILL_ENEMY) {
			char t = MarioLevelModel.getEnemyCharacters(false)[this.rand.nextInt(MarioLevelModel.getEnemyCharacters(false).length)];
			// turtle constraint
			if (t == MarioLevelModel.GREEN_KOOPA || t == MarioLevelModel.RED_KOOPA) {
			    if (turtleCount < this.maxTurtles) {
				turtleCount++;
			    } else {
				t = MarioLevelModel.GOOMBA;
			    }
			}
			boolean winged = rand.nextDouble() < CHANCE_WINGED;
			this.setBlock(x, y-1, MarioLevelModel.getWingedEnemyVersion(t, winged));
		    }
		}
	    }

	    x++;
	}

	// pipes
	lastY = 0;
	int lastlastY = 0;
	x = 0;
	int lastX = 0;
	for (Integer h : ground) {
	    if (x > minX && rand.nextDouble() < CHANCE_PIPE) {
		if (h == lastY && lastlastY <= lastY && x > (lastX + 1)) {
		    int height = PIPE_MIN_HEIGHT + (int) (Math.random() * PIPE_HEIGHT);
		    placePipe(x - 1, h, height);
		    lastX = x;
		}
	    }

	    lastlastY = lastY;
	    lastY = h;
	    x++;
	}

	// place enemies
	x = 0;
	for (Integer h : ground) {
	    if (x > minX && rand.nextDouble() < CHANCE_ENEMY) {
		char t = MarioLevelModel.getEnemyCharacters(false)[this.rand.nextInt(MarioLevelModel.getEnemyCharacters(false).length)];
		// turtle constraint
		if (t == MarioLevelModel.GREEN_KOOPA || t == MarioLevelModel.RED_KOOPA) {
		    if (turtleCount < this.maxTurtles) {
			turtleCount++;
		    } else {
			t = MarioLevelModel.GOOMBA;
		    }
		}
		boolean winged = rand.nextDouble() < CHANCE_WINGED;
		char tile = getBlock(x, h - 1);
		if (tile == MarioLevelModel.EMPTY) {
		    this.setBlock(x, h - 1, MarioLevelModel.getWingedEnemyVersion(t, winged));
		}
	    }
	    x++;
	}

	// platforms
	x = 0;
	y = levelHeight;
	for (Integer h : ground) {
	    int max = 0;

	    // find the highest object
	    for (max = 0; max < h; max++) {
		int tile = getBlock(x, max);
		if (tile != 0) {
		    break;
		}
	    }

	    if (y == levelHeight) {
		if (x > minX && rand.nextDouble() < CHANCE_PLATFORM) {
		    y = max - PLATFORM_HEIGHT; // (int)(-5*rand.nextDouble()*(h - 0));

		    if (y >= 1 && h - max > 1) {
			placeBlock(x, y);
		    } else {
			y = levelHeight;
		    }
		}
	    } else {
		// end if hitting a wall
		if (y >= (max + 1)) {
		    y = levelHeight;
		} else if (rand.nextDouble() < CHANCE_END_PLATFORM) {
		    placeBlock(x, y);
		    y = levelHeight;
		} else {
		    placeBlock(x, y);
		}
	    }
	    x++;
	}

	// coins
	x = 0;
	for (Integer h : ground) {
	    if (x > 5 && rand.nextDouble() < CHANCE_COIN) {
		y = h - (int) (1 + Math.random() * COIN_HEIGHT);

		char tile = getBlock(x, y);
		if (tile == MarioLevelModel.EMPTY) {
		    this.setBlock(x, y, MarioLevelModel.COIN);
		}
	    }

	    x++;
	}
	// place the exit
	this.xExit = this.map.length - 5;
	this.setBlock(this.xExit, this.yExit, MarioLevelModel.MARIO_EXIT);
	return MarioLevelModel.createStringMap(map);
    }

    public String getGeneratorName() {
	return "BenWeberLevelGenerator";
    }
}
