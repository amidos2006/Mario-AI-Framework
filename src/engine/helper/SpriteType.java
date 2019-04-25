package engine.helper;

import engine.core.MarioSprite;
import engine.sprites.Enemy;
import engine.sprites.FlowerEnemy;

public enum SpriteType {
    //Generic values
    NONE(0),
    UNDEF(-42),
    MARIO(-31), 
    //Common between detail 0 and 1
    FIREBALL(16),
    //Enemies Detail 0
    GOOMBA(2, 16), 
    GOOMBA_WINGED(3, 16), 
    RED_KOOPA(4, 0), 
    RED_KOOPA_WINGED(5, 0), 
    GREEN_KOOPA(6, 8), 
    GREEN_KOOPA_WINGED(7, 8),
    SPIKY(8, 24), 
    SPIKY_WINGED(9, 24),
    BULLET_BILL(10, 40), 
    ENEMY_FLOWER(11, 48),
    MUSHROOM(12), 
    FIRE_FLOWER(13), 
    SHELL(14), 
    LIFE_MUSHROOM(15),
    //Enemies Detail 1
    STOMPABLE_ENEMY(2),
    NONSTOMPABLE_ENEMY(8),
    SPECIAL_ITEM(12),
    //Enemies Detail 2
    ENEMY(1);

    private int value;
    private int startIndex;

    SpriteType(int newValue) {
	value = newValue;
    }

    SpriteType(int newValue, int newIndex) {
	value = newValue;
	startIndex = newIndex;
    }

    public int getValue() {
	return value;
    }

    public int getStartIndex() {
	return startIndex;
    }

    public MarioSprite spawnSprite(boolean visuals, int xTile, int yTile, int dir) {
	if (this == SpriteType.ENEMY_FLOWER) {
	    return new FlowerEnemy(visuals, xTile * 16 + 17, yTile * 16 + 18);
	}
	return new Enemy(visuals, xTile * 16 + 8, yTile * 16 + 15, dir, this);
    }

    public int getSpriteTypeGeneralization(int detail) {
	switch (detail) {
	case (0):
	    switch (this) {
	    case MARIO:
		return NONE.getValue();
	    default:
		this.getValue();
	    }
	case (1):
	    switch (this) {
	    case MARIO:
		return NONE.getValue();
	    case FIREBALL:
		return FIREBALL.getValue();
	    case MUSHROOM:
	    case LIFE_MUSHROOM:
	    case FIRE_FLOWER:
		return SPECIAL_ITEM.getValue();
	    case BULLET_BILL:
	    case SHELL:
	    case GOOMBA:
	    case GOOMBA_WINGED:
	    case GREEN_KOOPA:
	    case GREEN_KOOPA_WINGED:
	    case RED_KOOPA:
	    case RED_KOOPA_WINGED:
		return STOMPABLE_ENEMY.getValue();
	    case SPIKY:
	    case SPIKY_WINGED:
	    case ENEMY_FLOWER:
		return NONSTOMPABLE_ENEMY.getValue();
	    default:
		NONE.getValue();
	    }
	case (2):
	    switch (this) {
	    case FIREBALL:
	    case MARIO:
	    case MUSHROOM:
	    case LIFE_MUSHROOM:
	    case FIRE_FLOWER:
		return NONE.getValue();
	    default:
		return ENEMY.getValue();
	    }
	}
	return UNDEF.getValue();
    }
}
