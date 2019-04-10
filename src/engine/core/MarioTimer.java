package engine.core;

public class MarioTimer {
    private long startTimer;
    private long remainingTime;
    
    public MarioTimer(long remainingTime) {
	this.startTimer = System.currentTimeMillis();
	this.remainingTime = remainingTime;
    }
    
    public long getRemainingTime() {
	return this.remainingTime - (System.currentTimeMillis() - this.startTimer);
    }
}
