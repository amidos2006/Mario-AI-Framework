package engine.core;

import java.util.ArrayList;

import engine.helper.GameStatus;

public class MarioForwardModel {
    /**
     * The width of the observation grid
     */
    public final int obsGridWidth = MarioGame.tileWidth;
    /**
     * The height of the observation grid
     */
    public final int obsGridHeight = MarioGame.tileHeight;
    
    private MarioWorld world;
    
    /**
     * Create a forward model object
     * @param world the current level world that is being used. This class hides the world object so the agents won't cheat.
     */
    public MarioForwardModel(MarioWorld world) {
	this.world = world;
    }
    
    /**
     * Create a clone from the current forward model state
     * @return a clone from the current forward model state
     */
    public MarioForwardModel clone() {
	return new MarioForwardModel(this.world.clone());
    }
    
    /**
     * Advance the forward model using the action array
     * @param actions a list of all the button states
     */
    public void advance(boolean[] actions) {
	this.world.update(actions);
    }
    
    /**
     * Get the current state of the running game
     * @return GameStatus the current state (WIN, LOSE, TIME_OUT, RUNNING)
     */
    public GameStatus getGameStatus() {
	return this.world.gameStatus;
    }
    
    /**
     * The percentage of distance traversed between mario and the goal
     * @return value between 0 to 1 to indicate the percentage of distance traversed
     */
    public float getCompletionPercentage() {
	return this.world.mario.x / (this.world.level.exitTileX * 16);
    }
    
    /**
     * Get the current level dimensions
     * @return the first value is level width and second is level height
     */
    public float[] getLevelFloatDimensions() {
	return new float[] {this.world.level.width, this.world.level.height};
    }
    
    /**
     * Get the remaining time before the game timesout
     * @return the number of time ticks before timeout each frame removes 30 frames
     */
    public int getRemainingTime() {
	return this.world.currentTimer;
    }
    
    /**
     * Get mario position
     * @return the actual mario position in the current state
     */
    public float[] getMarioFloatPos() {
	return new float[] {this.world.mario.x, this.world.mario.y};
    }
    
    /**
     * Get mario velocity
     * @return the actual mario velocity in the current state
     */
    public float[] getMarioFloatVelocity() {
	return new float[] {this.world.mario.xa, this.world.mario.ya};
    }
    
    /**
     * If mario can press the jump button while in the air to reach higher areas
     * @return true if the agent can press the button longer and false otherwise
     */
    public boolean getMarioCanJumpHigher() {
	return this.world.mario.jumpTime > 0;
    }
    
    /**
     * Get the current mario mode
     * @return the current mario mode (0-small, 1-large, 2-fire)
     */
    public int getMarioMode() {
	int value=0;
	if(this.world.mario.isLarge) {
	    value = 1;
	}
	if(this.world.mario.isFire) {
	    value = 2;
	}
	return value;
    }
    
    /**
     * Get to know if mario is touching the ground.
     * @return true if mario is touching the ground and false otherwise
     */
    public boolean isMarioOnGround() {
	return this.world.mario.onGround;
    }
    
    /**
     * Get to know if mario is able to jump
     * @return true if mario can jump and false otherwise
     */
    public boolean mayMarioJump() {
	return this.world.mario.mayJump;
    }
    
    /**
     * Get a 3x float list that contain the type of enemies, x position, y position
     * @return an array of 3 floats that contain the enemy type, x position, y position for each enemy sprite
     */
    public float[] getEnemiesFloatPos() {
	ArrayList<MarioSprite> enemiesAlive = this.world.getEnemies();
	float[] enemyPos = new float[enemiesAlive.size() * 3];
	for(int i=0; i<enemiesAlive.size(); i++) {
	    enemyPos[3*i] = enemiesAlive.get(i).type.getValue();
	    enemyPos[3*i + 1] = enemiesAlive.get(i).x;
	    enemyPos[3*i + 2] = enemiesAlive.get(i).y;
	}
	return enemyPos;
    }
    
    /**
     * get the number of enemies killed in the game
     * @return number of enemies killed in the game
     */
    public int getKillsTotal() {
	return this.world.fallKill + this.world.stompKill + this.world.fireKill + this.world.shellKill;
    }
    
    /**
     * get the number of enemies killed by fireballs
     * @return number of enemies killed by fireballs
     */
    public int getKillsByFire() {
	return this.world.fireKill;
    }
    
    /**
     * get the number of enemies killed by stomping
     * @return number of enemies killed by stomping
     */
    public int getKillsByStomp() {
	return this.world.stompKill;
    }
    
    /**
     * get the number of enemies killed by a koopa shell
     * @return number of enemies killed by a koopa shell
     */
    public int getKillsByShell() {
	return this.world.shellKill;
    }
    
    /**
     * get the number of enemies that fell from the game screen
     * @return the number of enemies that fell from the game screen
     */
    public int getKillsByFall() {
	return this.world.fallKill;
    }
    
    /**
     * get the number 100 coins collected by mario
     * @return number of 100 coins collected by mario
     */
    public int getNumLives() {
	return this.world.lives;
    }
    
    /**
     * get the number of mushroom collected by mario
     * @return the number of collected mushrooms by mario
     */
    public int getNumCollectedMushrooms() {
	return this.world.mushrooms;
    }
    
    /**
     * get the number of fire flowers collected by mario
     * @return the number of collected fire flowers by mario
     */
    public int getNumCollectedFireflower() {
	return this.world.flowers;
    }
    
    /**
     * get the number of coins collected by mario
     * @return the number of collected coins by mario
     */
    public int getNumCollectedCoins() {
	return this.world.coins;
    }
    
    /**
     * get the number of destroyed bricks by large or fire mario
     * @return the number of destroyed bricks by large or fire mario
     */
    public int getNumDestroyedBricks() {
	return this.world.breakBlock;
    }
    
    /**
     * Get the tile location of mario with respect to the screen
     * @return the x and y location of mario on the screen as tile values
     */
    public int[] getMarioScreenTilePos() {
	return new int[] {(int)((this.world.mario.x - this.world.cameraX)/16), (int)(this.world.mario.y/16)};
    }
    
    /**
     * The current screen status as a 2D tile grid around the center of screen with scene detail value 1 and enemy detail value of 0
     * @return 2D grid that have all the information about all objects on the screen
     */
    public int[][] getScreenCompleteObservation(){
	return this.getScreenCompleteObservation(1, 0);
    }
    
    /**
     * The current enemies on the screen as a 2D tile grid around the center of screen with a detail value of 0
     * @return 2D grid where each tile contain either 0 to indicate no enemy or a number to indicate a certain enemy. Look at SpriteTypes for enemy values (Detail 0).
     */
    public int[][] getScreenEnemiesObservation(){
	return this.getScreenEnemiesObservation(0);
    }
    
    /**
     * The current objects (not enemies) on the screen as a 2D tile grid around the center of screen with a detail value of 1
     * @return 2D grid where each tile contain either 0 which means it is empty or a value that reflect the type of the tile in that area. Look at TileTypes for the meaning of values (Detail 1)
     */
    public int[][] getScreenSceneObservation(){
	return this.getScreenSceneObservation(1);
    }
    
    /**
     * The current screen status as a 2D tile grid around mario with scene detail value 1 and enemy detail value of 0
     * @return 2D grid that have all the information about all objects on the screen
     */
    public int[][] getMarioCompleteObservation(){
	return this.getMarioCompleteObservation(1, 0);
    }
    
    /**
     * The current enemies on the screen as a 2D tile grid around mario with a detail value of 0
     * @return 2D grid where each tile contain either 0 to indicate no enemy or a number to indicate a certain enemy. Look at SpriteTypes for enemy values (Detail 0).
     */
    public int[][] getMarioEnemiesObservation(){
	return this.getMarioEnemiesObservation(0);
    }
    
    /**
     * The current objects (not enemies) on the screen as a 2D tile grid around mario with a detail value of 1
     * @return 2D grid where each tile contain either 0 which means it is empty or a value that reflect the type of the tile in that area. Look at TileTypes for the meaning of values (Detail 1)
     */
    public int[][] getMarioSceneObservation(){
	return this.getMarioSceneObservation(1);
    }
    
    /**
     * The current screen status as a 2D tile grid around the center of screen
     * @param sceneDetail the detail level of the scene: 0 all detail, 1 less detailed, 2 binary detail
     * @param enemyDetail the detail level of the current enemies: 0 all details, 1 less detailed, 2 binary detail
     * @return 2D grid that have all the information about all objects on the screen
     */
    public int[][] getScreenCompleteObservation(int sceneDetail, int enemyDetail){
	return this.world.getMergedObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2, sceneDetail, enemyDetail);
    }
    
    /**
     * The current enemies on the screen as a 2D tile grid around the center of screen
     * @param detail the detail level of the current enemies: 0 all details, 1 less detailed, 2 binary detail
     * @return 2D grid where each tile contain either 0 to indicate no enemy or a number to indicate a certain enemy. Look at SpriteTypes for enemy values (Detail 0).
     */
    public int[][] getScreenEnemiesObservation(int detail){
	return this.world.getEnemiesObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2, detail);
    }
    
    /**
     * The current objects (not enemies) on the screen as a 2D tile grid around the center of screen
     * @param detail the detail level of the scene: 0 all detail, 1 less detailed, 2 binary detail
     * @return 2D grid where each tile contain either 0 which means it is empty or a value that reflect the type of the tile in that area. Look at TileTypes for the meaning of values (Detail 1)
     */
    public int[][] getScreenSceneObservation(int detail){
	return this.world.getSceneObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2, detail);
    }
    
    /**
     * The current screen status as a 2D tile grid around mario
     * @param sceneDetail the detail level of the scene: 0 all detail, 1 less detailed, 2 binary detail
     * @param enemyDetail the detail level of the current enemies: 0 all details, 1 less detailed, 2 binary detail
     * @return 2D grid that have all the information about all objects on the screen
     */
    public int[][] getMarioCompleteObservation(int sceneDetail, int enemyDetail){
	return this.world.getMergedObservation(this.world.mario.x, this.world.mario.y, sceneDetail, enemyDetail);
    }
    
    /**
     * The current enemies on the screen as a 2D tile grid around mario
     * @param detail the detail level of the current enemies: 0 all details, 1 less detailed, 2 binary detail
     * @return 2D grid where each tile contain either 0 to indicate no enemy or a number to indicate a certain enemy. Look at SpriteTypes for enemy values (Detail 0).
     */
    public int[][] getMarioEnemiesObservation(int detail){
	return this.world.getEnemiesObservation(this.world.mario.x, this.world.mario.y, detail);
    }
    
    /**
     * The current objects (not enemies) on the screen as a 2D tile grid around mario
     * @param detail the detail level of the scene: 0 all detail, 1 less detailed, 2 binary detail
     * @return 2D grid where each tile contain either 0 which means it is empty or a value that reflect the type of the tile in that area. Look at TileTypes for the meaning of values (Detail 1)
     */
    public int[][] getMarioSceneObservation(int detail){
	return this.world.getSceneObservation(this.world.mario.x, this.world.mario.y, detail);
    }
}
