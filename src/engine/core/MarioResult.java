package engine.core;

import java.util.ArrayList;

import engine.helper.EventType;
import engine.helper.GameStatus;
import engine.helper.SpriteType;

public class MarioResult {
    private MarioWorld world;
    private ArrayList<MarioEvent> gameEvents;
    private ArrayList<MarioAgentEvent> agentEvents;
    
    /**
     * Create a mario result object
     * @param world the current level world that is being used. This class uses the world object to get cleaner statistics.
     * @param all the events that happens in the playthrough of the game
     */
    public MarioResult(MarioWorld world, ArrayList<MarioEvent> gameEvents, ArrayList<MarioAgentEvent> agentEvents) {
	this.world = world;
	this.gameEvents = gameEvents;
	this.agentEvents = agentEvents;
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
     * Get all the game events that happen in the game
     * @return an arrayList of all possible events that happened in a mario game
     */
    public ArrayList<MarioEvent> getGameEvents(){
	return this.gameEvents;
    }
    
    /**
     * Get all the actions that the agent has been taking during the game
     * @return an arraylist that contains all the actions the agent has taken during game play
     */
    public ArrayList<MarioAgentEvent> getAgentEvents(){
	return this.agentEvents;
    }
    
    /**
     * get the number of enemies killed in the game
     * @return number of enemies killed in the game
     */
    public int getKillsTotal() {
	int kills = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.STOMP_KILL.getValue() || e.getEventType() == EventType.FIRE_KILL.getValue() || 
		    e.getEventType() == EventType.FALL_KILL.getValue() || e.getEventType() == EventType.SHELL_KILL.getValue()) {
		kills += 1;
	    }
	}
	return kills;
    }
    
    /**
     * get the number of enemies killed by fireballs
     * @return number of enemies killed by fireballs
     */
    public int getKillsByFire() {
	int kills = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.FIRE_KILL.getValue()) {
		kills += 1;
	    }
	}
	return kills;
    }
    
    /**
     * get the number of enemies killed by stomping
     * @return number of enemies killed by stomping
     */
    public int getKillsByStomp() {
	int kills = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.STOMP_KILL.getValue()) {
		kills += 1;
	    }
	}
	return kills;
    }
    
    /**
     * get the number of enemies killed by a koopa shell
     * @return number of enemies killed by a koopa shell
     */
    public int getKillsByShell() {
	int kills = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.SHELL_KILL.getValue()) {
		kills += 1;
	    }
	}
	return kills;
    }
    
    /**
     * get Num of kills for a certain enemy Type
     * @param enemyType the enemy type from SpriteType
     * @return number of a certain type of enemy that has been killed by Mario
     */
    public int getMarioNumKills(int enemyType) {
	int kills = 0;
	for (MarioEvent e : this.gameEvents) {
	    if ((e.getEventType() == EventType.SHELL_KILL.getValue()
		    || e.getEventType() == EventType.FIRE_KILL.getValue()
		    || e.getEventType() == EventType.STOMP_KILL.getValue()) && e.getEventParam() == enemyType) {
		kills += 1;
	    }
	}
	return kills;
    }
    
    /**
     * Get number of times mario got hit by an enemy
     * @return number of times mario got hurt
     */
    public int getMarioNumHurts() {
	int hurt = 0;
	for (MarioEvent e : this.gameEvents) {
	    if (e.getEventType() == EventType.HURT.getValue()) {
		hurt += 1;
	    }
	}
	return hurt;
    }
    
    /**
     * Number of times mario hit question mark block
     * @return number of question mark block mario hit
     */
    public int getNumBumpQuestionBlock() {
	int bump = 0;
	for (MarioEvent e : this.gameEvents) {
	    if (e.getEventType() == EventType.BUMP.getValue() && e.getEventParam() == MarioForwardModel.OBS_QUESTION_BLOCK) {
		bump += 1;
	    }
	}
	return bump;
    }
    
    /**
     * Number of times mario hit brick block
     * @return number of brick block mario hit
     */
    public int getNumBumpBrick() {
	int bump = 0;
	for (MarioEvent e : this.gameEvents) {
	    if (e.getEventType() == EventType.BUMP.getValue() && e.getEventParam() == MarioForwardModel.OBS_BRICK) {
		bump += 1;
	    }
	}
	return bump;
    }
    
    /**
     * get the number of enemies that fell from the game screen
     * @return the number of enemies that fell from the game screen
     */
    public int getKillsByFall() {
	int kills = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.FALL_KILL.getValue()) {
		kills += 1;
	    }
	}
	return kills;
    }
    
    /**
     * get number of jumps performed by mario during the game
     * @return the number of jumps performed by mario during the game
     */
    public int getNumJumps() {
	int jumps = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.JUMP.getValue()) {
		jumps += 1;
	    }
	}
	return jumps;
    }
    
    /**
     * get the maximum x distance traversed by mario
     * @return the maximum x distance traversed mario
     */
    public float getMaxXJump() {
	float maxXJump = 0;
	float startX = -100;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.JUMP.getValue()) {
		startX = e.getMarioX();
	    }
	    if(e.getEventType() == EventType.LAND.getValue()) {
		if(Math.abs(e.getMarioX() - startX) > maxXJump) {
		    maxXJump = Math.abs(e.getMarioX() - startX);
		}
	    }
	}
	return maxXJump;
    }
    
    /**
     * get the maximum amount of frames mario is being in the air
     * @return the maximum amount of frames mario is being in the air
     */
    public int getMaxJumpAirTime() {
	int maxAirJump = 0;
	int startTime = -100;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.JUMP.getValue()) {
		startTime = e.getTime();
	    }
	    if(e.getEventType() == EventType.LAND.getValue()) {
		if(e.getTime() - startTime > maxAirJump) {
		    maxAirJump = e.getTime() - startTime;
		}
	    }
	}
	return maxAirJump;
    }
    
    /**
     * get the number 100 coins collected by mario and 1 ups found
     * @return number of 100 coins collected by mario and 1 ups found
     */
    public int getCurrentLives() {
	return this.world.lives;
    }
    
    /**
     * get the number of coins that mario have by end of the game
     * @return the number of coins that mario have by end of the game
     */
    public int getCurrentCoins() {
	return this.world.coins;
    }
    
    /**
     * get the number of mushroom collected by mario
     * @return the number of collected mushrooms by mario
     */
    public int getNumCollectedMushrooms() {
	int collect = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.COLLECT.getValue() && e.getEventParam() == SpriteType.MUSHROOM.getValue()) {
		collect += 1;
	    }
	}
	return collect;
    }
    
    /**
     * get the number of fire flowers collected by mario
     * @return the number of collected fire flowers by mario
     */
    public int getNumCollectedFireflower() {
	int collect = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.COLLECT.getValue() && e.getEventParam() == SpriteType.FIRE_FLOWER.getValue()) {
		collect += 1;
	    }
	}
	return collect;
    }
    
    /**
     * get the number of coins collected by mario
     * @return the number of collected coins by mario
     */
    public int getNumCollectedTileCoins() {
	int collect = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.COLLECT.getValue() && e.getEventParam() == MarioForwardModel.OBS_COIN) {
		collect += 1;
	    }
	}
	return collect;
    }
    
    /**
     * get the number of destroyed bricks by large or fire mario
     * @return the number of destroyed bricks by large or fire mario
     */
    public int getNumDestroyedBricks() {
	int bricks = 0;
	for(MarioEvent e:this.gameEvents) {
	    if(e.getEventType() == EventType.BUMP.getValue() && 
		    e.getEventParam() == MarioForwardModel.OBS_BRICK && e.getMarioState() > 0) {
		bricks += 1;
	    }
	}
	return bricks;
    }
}
