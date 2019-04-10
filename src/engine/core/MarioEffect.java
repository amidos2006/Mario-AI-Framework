package engine.core;

import java.awt.Graphics;

import engine.graphics.MarioImage;
import engine.helper.Assets;

public abstract class MarioEffect {
    public float x, y, xv, yv, xa, ya;
    public int life, startingIndex;
    protected MarioImage graphics;
    
    public MarioEffect(float x, float y, float xv, float yv, float xa, float ya, int startIndex, int life) {
	this.x = x;
	this.y = y;
	this.xv = xv;
	this.yv = yv;
	this.xa = xa;
	this.ya = ya;
	this.life = life;
	
	this.graphics = new MarioImage(Assets.particles, startIndex);
	this.graphics.width = 16;
	this.graphics.height = 16;
	this.graphics.originX = 8;
	this.graphics.originY = 8;
	this.startingIndex = startIndex;
    }
    
    public void render(Graphics og, float cameraX, float cameraY) {
	if(this.life <= 0) {
	    return;
	}
	this.life -= 1;
	this.x += this.xv;
	this.y += this.yv;
	this.xv += this.xa;
	this.yv += this.ya;

	graphics.render(og, (int)(this.x - cameraX), (int)(this.y - cameraY));
    }
}
