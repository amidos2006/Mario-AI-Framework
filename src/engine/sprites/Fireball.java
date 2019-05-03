package engine.sprites;

import java.awt.Graphics;

import engine.core.MarioSprite;
import engine.graphics.MarioImage;
import engine.helper.Assets;
import engine.helper.SpriteType;

public class Fireball extends MarioSprite {

    private static final float GROUND_INERTIA = 0.89f;
    private static final float AIR_INERTIA = 0.89f;

    private boolean onGround = false;
    private MarioImage graphics;
    private int anim = 0;

    public Fireball(boolean visuals, float x, float y, int facing) {
	super(x, y, SpriteType.FIREBALL);
	this.facing = facing;
	this.ya = 4;
	this.width = 4;
	this.height = 8;

	if (visuals) {
	    this.graphics = new MarioImage(Assets.particles, 24);
	    this.graphics.originX = 8;
	    this.graphics.originY = 8;
	    this.graphics.width = 16;
	    this.graphics.height = 16;
	}
    }

    @Override
    public MarioSprite clone() {
	Fireball f = new Fireball(false, this.x, this.y, this.facing);
	f.xa = this.xa;
	f.ya = this.ya;
	f.initialCode = this.initialCode;
	f.width = this.width;
	f.height = this.height;
	f.onGround = this.onGround;
	return (MarioSprite)f;
    }

    private boolean move(float xa, float ya) {
	while (xa > 8) {
	    if (!move(8, 0))
		return false;
	    xa -= 8;
	}
	while (xa < -8) {
	    if (!move(-8, 0))
		return false;
	    xa += 8;
	}
	while (ya > 8) {
	    if (!move(0, 8))
		return false;
	    ya -= 8;
	}
	while (ya < -8) {
	    if (!move(0, -8))
		return false;
	    ya += 8;
	}

	boolean collide = false;
	if (ya > 0) {
	    if (isBlocking(x + xa - width, y + ya, xa, 0))
		collide = true;
	    else if (isBlocking(x + xa + width, y + ya, xa, 0))
		collide = true;
	    else if (isBlocking(x + xa - width, y + ya + 1, xa, ya))
		collide = true;
	    else if (isBlocking(x + xa + width, y + ya + 1, xa, ya))
		collide = true;
	}
	if (ya < 0) {
	    if (isBlocking(x + xa, y + ya - height, xa, ya))
		collide = true;
	    else if (collide || isBlocking(x + xa - width, y + ya - height, xa, ya))
		collide = true;
	    else if (collide || isBlocking(x + xa + width, y + ya - height, xa, ya))
		collide = true;
	}
	if (xa > 0) {
	    if (isBlocking(x + xa + width, y + ya - height, xa, ya))
		collide = true;
	    if (isBlocking(x + xa + width, y + ya - height / 2, xa, ya))
		collide = true;
	    if (isBlocking(x + xa + width, y + ya, xa, ya))
		collide = true;
	}
	if (xa < 0) {
	    if (isBlocking(x + xa - width, y + ya - height, xa, ya))
		collide = true;
	    if (isBlocking(x + xa - width, y + ya - height / 2, xa, ya))
		collide = true;
	    if (isBlocking(x + xa - width, y + ya, xa, ya))
		collide = true;
	}

	if (collide) {
	    if (xa < 0) {
		x = (int) ((x - width) / 16) * 16 + width;
		this.xa = 0;
	    }
	    if (xa > 0) {
		x = (int) ((x + width) / 16 + 1) * 16 - width - 1;
		this.xa = 0;
	    }
	    if (ya < 0) {
		y = (int) ((y - height) / 16) * 16 + height;
		this.ya = 0;
	    }
	    if (ya > 0) {
		y = (int) (y / 16 + 1) * 16 - 1;
		onGround = true;
	    }
	    return false;
	} else {
	    x += xa;
	    y += ya;
	    return true;
	}
    }

    private boolean isBlocking(float _x, float _y, float xa, float ya) {
	int x = (int) (_x / 16);
	int y = (int) (_y / 16);
	if (x == (int) (this.x / 16) && y == (int) (this.y / 16))
	    return false;

	boolean blocking = world.level.isBlocking(x, y, xa, ya);

	return blocking;
    }

    @Override
    public void update() {
	if (facing != 0)
	    anim++;

	float sideWaysSpeed = 8f;
	if (xa > 2) {
	    facing = 1;
	}
	if (xa < -2) {
	    facing = -1;
	}
	xa = facing * sideWaysSpeed;

	world.checkFireballCollide(this);

	if (!move(xa, 0)) {
	    this.world.removeSprite(this);
	    return;
	}

	onGround = false;
	move(0, ya);
	if (onGround)
	    ya = -10;

	ya *= 0.95f;
	if (onGround) {
	    xa *= GROUND_INERTIA;
	} else {
	    xa *= AIR_INERTIA;
	}

	if (!onGround) {
	    ya += 1.5;
	}

	if (this.graphics != null) {
	    this.graphics.flipX = facing == -1;
	    this.graphics.index = 24 + anim % 4;
	}
    }

    @Override
    public void render(Graphics og) {
	super.render(og);
	this.graphics.render(og, (int) (this.x - this.world.cameraX), (int) (this.y - this.world.cameraY));
    }
    // private float runTime;
    // private boolean onGround = false;
    //
    // private int width = 4;
    // int height = 24;
    //
    // private LevelScene world;
    // public int facing;
    //
    // public boolean avoidCliffs = false;
    // public int anim;
    //
    // public boolean dead = false;
    // private int deadTime = 0;
    //
    // public Fireball(LevelScene world, float x, float y, int facing)
    // {
    // kind = KIND_FIREBALL;
    // sheet = Art.particles;
    //
    // this.x = x;
    // this.y = y;
    // this.world = world;
    // xPicO = 4;
    // yPicO = 4;
    //
    // yPic = 3;
    // height = 8;
    // this.facing = facing;
    // wPic = 8;
    // hPic = 8;
    //
    // xPic = 4;
    // ya = 4;
    // }
    //
    // public void move()
    // {
    // if (deadTime > 0)
    // {
    // for (int i = 0; i < 8; i++)
    // {
    // world.addSprite(new Sparkle((int) (x + Math.random() * 8 - 4)+4, (int) (y +
    // Math.random() * 8-4)+2, (float) Math.random() * 2 - 1-facing, (float)
    // Math.random() *2 -1, 0, 1, 5));
    // }
    // spriteContext.removeSprite(this);
    //
    // return;
    // }
    //
    // if (facing != 0) anim++;
    //
    // float sideWaysSpeed = 8f;
    // // float sideWaysSpeed = onGround ? 2.5f : 1.2f;
    //
    // if (xa > 2)
    // {
    // facing = 1;
    // }
    // if (xa < -2)
    // {
    // facing = -1;
    // }
    //
    // xa = facing * sideWaysSpeed;
    //
    // world.checkFireballCollide(this);
    //
    // xFlipPic = facing == -1;
    //
    // runTime += (Math.abs(xa)) + 5;
    //
    // xPic = (anim) % 4;
    //
    //
    //
    // if (!move(xa, 0))
    // {
    // die();
    // }
    //
    // onGround = false;
    // move(0, ya);
    // if (onGround) ya = -10;
    //
    // ya *= 0.95f;
    // if (onGround)
    // {
    // xa *= GROUND_INERTIA;
    // }
    // else
    // {
    // xa *= AIR_INERTIA;
    // }
    //
    // if (!onGround)
    // {
    // ya += 1.5;
    // }
    // }
    //
    // private boolean move(float xa, float ya)
    // {
    // while (xa > 8)
    // {
    // if (!move(8, 0)) return false;
    // xa -= 8;
    // }
    // while (xa < -8)
    // {
    // if (!move(-8, 0)) return false;
    // xa += 8;
    // }
    // while (ya > 8)
    // {
    // if (!move(0, 8)) return false;
    // ya -= 8;
    // }
    // while (ya < -8)
    // {
    // if (!move(0, -8)) return false;
    // ya += 8;
    // }
    //
    // boolean collide = false;
    // if (ya > 0)
    // {
    // if (isBlocking(x + xa - width, y + ya, xa, 0)) collide = true;
    // else if (isBlocking(x + xa + width, y + ya, xa, 0)) collide = true;
    // else if (isBlocking(x + xa - width, y + ya + 1, xa, ya)) collide = true;
    // else if (isBlocking(x + xa + width, y + ya + 1, xa, ya)) collide = true;
    // }
    // if (ya < 0)
    // {
    // if (isBlocking(x + xa, y + ya - height, xa, ya)) collide = true;
    // else if (collide || isBlocking(x + xa - width, y + ya - height, xa, ya))
    // collide = true;
    // else if (collide || isBlocking(x + xa + width, y + ya - height, xa, ya))
    // collide = true;
    // }
    // if (xa > 0)
    // {
    // if (isBlocking(x + xa + width, y + ya - height, xa, ya)) collide = true;
    // if (isBlocking(x + xa + width, y + ya - height / 2, xa, ya)) collide = true;
    // if (isBlocking(x + xa + width, y + ya, xa, ya)) collide = true;
    //
    // if (avoidCliffs && onGround && !world.level.isBlocking((int) ((x + xa +
    // width) / 16), (int) ((y) / 16 + 1), xa, 1)) collide = true;
    // }
    // if (xa < 0)
    // {
    // if (isBlocking(x + xa - width, y + ya - height, xa, ya)) collide = true;
    // if (isBlocking(x + xa - width, y + ya - height / 2, xa, ya)) collide = true;
    // if (isBlocking(x + xa - width, y + ya, xa, ya)) collide = true;
    //
    // if (avoidCliffs && onGround && !world.level.isBlocking((int) ((x + xa -
    // width) / 16), (int) ((y) / 16 + 1), xa, 1)) collide = true;
    // }
    //
    // if (collide)
    // {
    // if (xa < 0)
    // {
    // x = (int) ((x - width) / 16) * 16 + width;
    // this.xa = 0;
    // }
    // if (xa > 0)
    // {
    // x = (int) ((x + width) / 16 + 1) * 16 - width - 1;
    // this.xa = 0;
    // }
    // if (ya < 0)
    // {
    // y = (int) ((y - height) / 16) * 16 + height;
    // this.ya = 0;
    // }
    // if (ya > 0)
    // {
    // y = (int) (y / 16 + 1) * 16 - 1;
    // onGround = true;
    // }
    // return false;
    // }
    // else
    // {
    // x += xa;
    // y += ya;
    // return true;
    // }
    // }
    //
    // private boolean isBlocking(float _x, float _y, float xa, float ya)
    // {
    // int x = (int) (_x / 16);
    // int y = (int) (_y / 16);
    // if (x == (int) (this.x / 16) && y == (int) (this.y / 16)) return false;
    //
    // boolean blocking = world.level.isBlocking(x, y, xa, ya);
    //
    // byte block = world.level.getBlock(x, y);
    //
    // return blocking;
    // }
    //
    // public void die()
    // {
    // dead = true;
    //
    // xa = -facing * 2;
    // ya = -5;
    // deadTime = 100;
    // }
}
