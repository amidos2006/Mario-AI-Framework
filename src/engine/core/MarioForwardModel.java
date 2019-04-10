package engine.core;

import java.util.ArrayList;

import engine.helper.GameStatus;

public class MarioForwardModel {
    private MarioWorld world;
    
    public MarioForwardModel(MarioWorld world) {
	this.world = world;
    }
    
    public MarioForwardModel copy() {
	return new MarioForwardModel(this.world.clone());
    }
    
    public void advance(boolean[] actions) {
	this.world.update(actions);
    }
    
    public GameStatus getGameStatus() {
	return this.world.gameStatus;
    }
    
    public float[] getMarioFloatPos() {
	return new float[] {this.world.mario.x, this.world.mario.y};
    }
    
    public int[] getMarioScreenTilePos() {
	return new int[] {(int)((this.world.mario.x - this.world.cameraX)/16), (int)(this.world.mario.y/16)};
    }
    
    public float[] getEnemiesFloatPos() {
	ArrayList<MarioSprite> enemiesAlive = this.world.getEnemies();
	float[] enemyPos = new float[enemiesAlive.size() * 2];
	for(int i=0; i<enemiesAlive.size(); i++) {
	    enemyPos[2*i] = enemiesAlive.get(0).x;
	    enemyPos[2*i + 1] = enemiesAlive.get(0).y;
	}
	return enemyPos;
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
    
    public boolean mayMarioJump() {
	return this.world.mario.mayJump;
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
    
    public int[][] getScreenCompleteObservation(){
	return this.getScreenCompleteObservation(1, 0);
    }
    
    public int[][] getScreenEnemiesObservation(){
	return this.getScreenEnemiesObservation(0);
    }
    
    public int[][] getScreenSceneObservation(){
	return this.getScreenSceneObservation(1);
    }
    
    public int[][] getMarioCompleteObservation(){
	return this.getMarioCompleteObservation(1, 0);
    }
    
    public int[][] getMarioEnemiesObservation(){
	return this.getMarioEnemiesObservation(0);
    }
    
    public int[][] getMarioSceneObservation(){
	return this.getMarioSceneObservation(1);
    }
    
    public int[][] getScreenCompleteObservation(int sceneDetail, int enemyDetail){
	return this.world.getMergedObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2, sceneDetail, enemyDetail);
    }
    
    public int[][] getScreenEnemiesObservation(int detail){
	return this.world.getEnemiesObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2, detail);
    }
    
    public int[][] getScreenSceneObservation(int detail){
	return this.world.getSceneObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2, detail);
    }
    
    public int[][] getMarioCompleteObservation(int sceneDetail, int enemyDetail){
	return this.world.getMergedObservation(this.world.mario.x, this.world.mario.y, sceneDetail, enemyDetail);
    }
    
    public int[][] getMarioEnemiesObservation(int detail){
	return this.world.getEnemiesObservation(this.world.mario.x, this.world.mario.y, detail);
    }
    
    public int[][] getMarioSceneObservation(int detail){
	return this.world.getSceneObservation(this.world.mario.x, this.world.mario.y, detail);
    }
}
