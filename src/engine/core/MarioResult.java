package engine.core;

import engine.helper.GameStatus;

public class MarioResult {
    private MarioWorld world;
    
    public MarioResult(MarioWorld world) {
	this.world = world;
    }
    
    public GameStatus getGameStatus() {
	return this.world.gameStatus;
    }
    
    public float getCompletionPercentage() {
	return this.world.mario.x / (this.world.level.exitTileX * 16);
    }
            
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
    
    public boolean isMarioOnGround() {
	return this.world.mario.onGround;
    }
    
    public int getRemainingTime() {
	return this.world.currentTimer;
    }
    
    public int getKillsTotal() {
	return this.world.fallKill + this.world.stompKill + this.world.fireKill + this.world.shellKill;
    }
    
    public int getKillsByFire() {
	return this.world.fireKill;
    }
    
    public int getKillsByStomp() {
	return this.world.stompKill;
    }
    
    public int getKillsByShell() {
	return this.world.shellKill;
    }
    
    public int getKillsByFall() {
	return this.world.fallKill;
    }
    
    public int getNumJumps() {
	return this.world.numJumps;
    }
    
    public int getMaxXJump() {
	return this.world.maxXJump;
    }
    
    public int getJumpAirTime() {
	return this.world.jumpAirTime;
    }
    
    public int getNumLives() {
	return this.world.lives;
    }
    
    public int getNumCollectedMushrooms() {
	return this.world.mushrooms;
    }
    
    public int getNumCollectedFireflower() {
	return this.world.flowers;
    }
    
    public int getNumCollectedCoins() {
	return this.world.coins;
    }
    
    public int getNumDestroyedBricks() {
	return this.world.breakBlock;
    }
}
