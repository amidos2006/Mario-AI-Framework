package engine.core;

public class MarioAgentEvent {
    private boolean[] actions;
    private float marioX;
    private float marioY;
    private int marioState;
    private boolean marioOnGround;
    private int time;
    
    public MarioAgentEvent(boolean[] actions, float marioX, float marioY, int marioState, boolean marioOnGround, int time) {
	this.actions = actions;
	this.marioX = marioX;
	this.marioY = marioY;
	this.marioState = marioState;
	this.marioOnGround = marioOnGround;
	this.time = time;
    }
    
    public boolean[] getActions() {
	return this.actions;
    }
    
    public float getMarioX() {
	return this.marioX;
    }
    
    public float getMarioY() {
	return this.marioY;
    }
    
    public int getMarioState() {
	return this.marioState;
    }
    
    public boolean getMarioOnGround() {
	return this.marioOnGround;
    }
    
    public int getTime() {
	return this.time;
    }
}
