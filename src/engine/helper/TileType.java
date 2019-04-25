package engine.helper;

public enum TileType {
    //Generic values
    NONE(0),
    UNDEF(-42),
    // Common between level 0 and level 1
    SOLID(16 + 1),
    BRICK(16 + 6), 
    QUESTION_BLOCK(16 + 8),
    COIN(16 + 15),
    // Scene detail level 0
    PYRAMID_SOLID(16 + 2),
    PIPE_BODY_RIGHT(16 + 21),
    PIPE_BODY_LEFT(16 + 20),
    PIPE_TOP_RIGHT(16 + 19),
    PIPE_TOP_LEFT(16 + 18),
    USED_BLOCK(16 + 14),
    BULLET_BILL_BODY(16 + 5),
    BULLET_BILL_NECT(16 + 4),
    BULLET_BILL_HEAD(16 + 3),
    BACKGROUND(16 + 47),
    PLATFORM_SINGLE(16 + 43),
    PLATFORM_LEFT(16 + 44),
    PLATFORM_RIGHT(16 + 45),
    PLATFORM_CENTER(16 + 46),
    // Scene detail level 1
    PLATFORM(16 + 43),
    BULLET_BILL(16 + 3),
    PIPE(16 + 18),
    // Scene detail level 2
    SCENE_OBJECT(100);
    
    private int value;

    TileType(int newValue) {
	value = newValue;
    }
    
    public int getValue() {
	return value;
    }
}
