package engine.core;

import engine.helper.GameStatus;

public class MarioResult {
    private MarioWorld world;
    
    /**
     * Create a mario result object
     * @param world the current level world that is being used. This class uses the world object to get cleaner statistics.
     */
    public MarioResult(MarioWorld world) {
	this.world = world;
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
     * Get the remaining time before the game timesout
     * @return the number of time ticks before timeout each frame removes 30 frames
     */
    public int getRemainingTime() {
	return this.world.currentTimer;
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
     * get number of jumps performed by mario during the game
     * @return the number of jumps performed by mario during the game
     */
    public int getNumJumps() {
	return this.world.numJumps;
    }
    
    /**
     * get the maximum x distance traversed by mario
     * @return the maximum x distance traversed mario
     */
    public int getMaxXJump() {
	return this.world.maxXJump;
    }
    
    /**
     * get the maximum amount of frames mario is being in the air
     * @return the maximum amount of frames mario is being in the air
     */
    public int getMaxJumpAirTime() {
	return this.world.jumpAirTime;
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
}
