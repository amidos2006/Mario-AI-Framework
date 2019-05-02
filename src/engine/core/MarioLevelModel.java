package engine.core;

public class MarioLevelModel {
    //start and end of the level
    public static final char MARIO_START = 'M';
    public static final char MARIO_EXIT = 'F';
    public static final char EMPTY = '-';
    
    //game tiles symbols
    public static final char GROUND = 'X';
    public static final char PYRAMID_BLOCK = '#';
    public static final char NORMAL_BRICK = 'S';
    public static final char COIN_BRICK = 'C';
    public static final char LIFE_BRICK = 'L';
    public static final char SPECIAL_BRICK = 'U';
    public static final char SPECIAL_QUESTION_BLOCK = '@';
    public static final char COIN_QUESTION_BLOCK = '!';
    public static final char COIN_HIDDEN_BLOCK = '2';
    public static final char LIFE_HIDDEN_BLOCK = '1';
    public static final char USED_BLOCK = 'D';
    public static final char COIN = 'o';
    public static final char PIPE = 't';
    public static final char PIPE_FLOWER = 'T';
    public static final char BULLET_BILL = '*';
    public static final char PLATFORM_BACKGROUND = '|';
    public static final char PLATFORM = '%';
    
    //enemies that can be in the level
    public static final char GOOMBA = 'g';
    public static final char GOOMBA_WINGED = 'G';
    public static final char RED_KOOPA = 'r';
    public static final char RED_KOOPA_WINGED = 'R';
    public static final char GREEN_KOOPA = 'k';
    public static final char GREEN_KOOPA_WINGED = 'K';
    public static final char SPIKY = 'y';
    public static final char SPIKY_WINGED = 'Y';
    
    /**
     * Get array of level tiles that can spawn enemies
     * @return tiles that spawn enemies
     */
    public static char[] getEnemyTiles() {
	return new char[] {BULLET_BILL, PIPE_FLOWER};
    }
    
    /**
     * list of tiles that can be bumped by the player
     * @return list of tiles that can be bumped by the player
     */
    public static char[] getBumpableTiles() {
	return new char[] {NORMAL_BRICK, COIN_BRICK, LIFE_BRICK, SPECIAL_BRICK, 
		SPECIAL_QUESTION_BLOCK, COIN_QUESTION_BLOCK};
    }
    
    /**
     * list all the tiles that can block the player movement
     * @return array of all tiles that block player movement
     */
    public static char[] getBlockTiles() {
	return new char[] {GROUND, PYRAMID_BLOCK, USED_BLOCK,
		NORMAL_BRICK, COIN_BRICK, LIFE_BRICK, SPECIAL_BRICK, 
		SPECIAL_QUESTION_BLOCK, COIN_QUESTION_BLOCK,
		PIPE, PIPE_FLOWER, BULLET_BILL};
    }
    
    /**
     * tiles that block the player and not interactive
     * @return list of all solid tiles that don't interact
     */
    public static char[] getBlockNonSpecialTiles() {
	return new char[] {GROUND, PYRAMID_BLOCK, USED_BLOCK, PIPE};
    }
    
    /**
     * list of all tiles that won't block the player movement
     * @return list of all non blocking tiles
     */
    public static char[] getNonBlockingTiles() {
	return new char[] {COIN, COIN_HIDDEN_BLOCK, LIFE_HIDDEN_BLOCK, PLATFORM_BACKGROUND};
    }
    
    /**
     * Get a list of all scene tiles that could produce something collected by the player
     * @return list of all collectible scene tiles
     */
    public static char[] getCollectablesTiles() {
	return new char[] {COIN, 
		COIN_BRICK, LIFE_BRICK, SPECIAL_BRICK, 
		SPECIAL_QUESTION_BLOCK, COIN_QUESTION_BLOCK, 
		COIN_HIDDEN_BLOCK, LIFE_HIDDEN_BLOCK};
    }
    
    /**
     * Get the correct version of the enemy char
     * @param enemy the enemy character
     * @param winged boolean to indicate if its a winged enemy
     * @return correct character based on winged
     */
    public static char getWingedEnemyVersion(char enemy, boolean winged) {
	if(!winged) {
	    if (enemy == GOOMBA_WINGED) {
		return GOOMBA;
	    }
	    if (enemy == GREEN_KOOPA_WINGED) {
		return GREEN_KOOPA;
	    }
	    if (enemy == RED_KOOPA_WINGED) {
		return RED_KOOPA;
	    }
	    if (enemy == SPIKY_WINGED) {
		return SPIKY;
	    }
	    return enemy;
	}
	if(enemy == GOOMBA){
	    return GOOMBA_WINGED;
	}
	if(enemy == GREEN_KOOPA) {
	    return GREEN_KOOPA_WINGED;
	}
	if(enemy == RED_KOOPA) {
	    return RED_KOOPA_WINGED;
	}
	if(enemy == SPIKY) {
	    return SPIKY_WINGED;
	}
	return enemy;
    }
    
    /**
     * a list of all enemy characters
     * @return array of all enemy characters
     */
    public static char[] getEnemyCharacters() {
	return new char[] {GOOMBA, GOOMBA_WINGED, RED_KOOPA, RED_KOOPA_WINGED, 
		GREEN_KOOPA, GREEN_KOOPA_WINGED, SPIKY, SPIKY_WINGED};
    }
    
    /**
     * list of all enemy character based on wings
     * @param wings true if the list contain winged enemies and false otherwise
     * @return an array of all wings enemy or not winged
     */
    public static char[] getEnemyCharacters(boolean wings) {
	if(wings) {
	    return new char[] {GOOMBA_WINGED, RED_KOOPA_WINGED, GREEN_KOOPA_WINGED, SPIKY_WINGED};
	}
	return new char[] {GOOMBA, RED_KOOPA, GREEN_KOOPA, SPIKY};
    }
    
    /**
     * map object for helping
     */
    private char[][] map;
    
    /**
     * create the Level Model
     * @param levelWidth the width of the level
     * @param levelHeight the height of the level
     */
    public MarioLevelModel(int levelWidth, int levelHeight) {
	this.map = new char[levelWidth][levelHeight];
    }
    
    /**
     * create a similar clone to the current map
     */
    public MarioLevelModel clone() {
	MarioLevelModel model = new MarioLevelModel(this.getWidth(), this.getHeight());
	for(int x=0; x<model.getWidth(); x++) {
	    for(int y=0; y<model.getHeight(); y++) {
		model.map[x][y] = this.map[x][y];
	    }
	}
	return model;
    }
    
    /**
     * get map width
     * @return map width
     */
    public int getWidth() {
	return this.map.length;
    }
    
    /**
     * get map height 
     * @return map height
     */
    public int getHeight() {
	return this.map[0].length;
    }
    
    /**
     * get the value of the tile in certain location
     * @param x x tile position
     * @param y y tile position
     * @return the tile value
     */
    public char getBlock(int x, int y) {
	int currentX = x;
	int currentY = y;
	if(x < 0) currentX = 0;
	if(y < 0) currentY = 0;
	if(x > this.map.length - 1) currentX = this.map.length - 1;
	if(y > this.map[0].length - 1) currentY = this.map[0].length - 1;
	return this.map[currentX][currentY];
    }
    
    /**
     * set a tile on the map with certain value
     * @param x the x tile position
     * @param y the y tile position
     * @param value the tile value to be set
     */
    public void setBlock(int x, int y, char value) {
	if(x < 0 || y < 0 || x > this.map.length - 1 || y > this.map[0].length - 1) return;
	this.map[x][y] = value;
    }
    
    /**
     * set a rectangle area of the map with a certain tile value
     * @param startX the x tile position of the left upper corner
     * @param startY the y tile position of the left upper corner
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param value the tile value
     */
    public void setRectangle(int startX, int startY, int width, int height, char value) {
	for(int x=0; x<width; x++) {
	    for(int y=0; y<height; y++) {
		this.setBlock(startX + x, startY + y, value);
	    }
	}
    }
    
    /**
     * Copy the string level to the current map
     * @param level the input string level
     */
    public void copyFromString(String level) {
	this.copyFromString(0, 0, 0, 0, this.getWidth(), this.getHeight(), level);
    }
    
    /**
     * Copy portion from string to the current map
     * @param targetX the x of the target location
     * @param targetY the y of the target location
     * @param sourceX the x from the level string
     * @param sourceY the y from the level string
     * @param width the width of the copied portion
     * @param height the height of the copied protion
     * @param level the level string
     */
    public void copyFromString(int targetX, int targetY, int sourceX, int sourceY, int width, int height, String level) {
	String[] lines = level.split("\n");
	for(int y=0; y<height; y++) {
	    for(int x=0; x<width; x++) {
		int maxWidth = lines[0].length();
		int maxHeight = lines.length;
		this.setBlock(x + targetX, y + targetY, lines[Math.min(y + sourceY, maxHeight - 1)].charAt(Math.min(x + sourceX, maxWidth - 1)));
	    }
	}
    }
    
    /**
     * clear the whole map
     */
    public void clearMap() {
	this.setRectangle(0, 0, this.getWidth(), this.getHeight(), EMPTY);
    }
    
    /**
     * get the string value of the map
     * @return the map in form of string
     */
    public String getMap() {
	String result = "";
	for(int y=0; y<map[0].length; y++) {
	    for(int x=0; x<map.length; x++) {
		result += map[x][y];
	    }
	    result += "\n";
	}
	return result;
    }
    
    /**
     * test the current level using a specific agent
     * @param agent agent to test the level
     * @param timer amount of time allowed to test that level
     * @return statistical results about the level
     */
    public MarioResult testALevelWithAgent(MarioAgent agent, int timer) {
	MarioGame game = new MarioGame();
	return game.runGame(agent, this.getMap(), timer);
    }
}
