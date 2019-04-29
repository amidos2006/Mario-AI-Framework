package engine.core;

public class MarioLevelModel {
    //start and end of the level
    public static final char MARIO_START = 'M';
    public static final char MARIO_EXIT = 'F';
    public static final char EMPTY = '-';
    
    //game tiles symbols
    public static final char GROUND = 'X';
    public static final char PYRAMID_BLOCK = '#';
    public static final char NORMAL_BRICK = 'B';
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
     * create an empty 2D char array
     * @param width the 1st dimension of the 2D array
     * @param height the 2nd dimension of the 2D array
     * @return 2D char array filled with EMPTY
     */
    public static char[][] createEmptyMap(int width, int height){
	char[][] map = new char[width][height];
	for(int x=0; x<map.length; x++) {
	    for(int y=0; y<map[x].length; y++) {
		map[x][y] = '-';
	    }
	}
	return map;
    }
    
    /**
     * create a string from 2D char array
     * @param map 2D char array where 1st dimension is width and 2nd is height
     * @return string that is equivalent to map
     */
    public static String createStringMap(char[][] map) {
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
     * test a level using a specific agent
     * @param agent agent to test the level
     * @param level current level to test
     * @param timer amount of time allowed to test that level
     * @return statistical results about the level
     */
    public static MarioResult testALevelWithAnAgent(MarioAgent agent, String level, int timer) {
	MarioGame game = new MarioGame();
	return game.runGame(agent, level, timer);
    }
}
