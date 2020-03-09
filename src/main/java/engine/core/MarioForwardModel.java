package engine.core;

import java.util.ArrayList;

import engine.helper.EventType;
import engine.helper.GameStatus;
import engine.helper.SpriteType;

public class MarioForwardModel {
    private static final int OBS_SCENE_SHIFT = 16;

    // Generic values
    public static final int OBS_NONE = 0;
    public static final int OBS_UNDEF = -42;

    // Common between scene detail level 0 and scene detail level 1
    public static final int OBS_SOLID = OBS_SCENE_SHIFT + 1;
    public static final int OBS_BRICK = OBS_SCENE_SHIFT + 6;
    public static final int OBS_QUESTION_BLOCK = OBS_SCENE_SHIFT + 8;
    public static final int OBS_COIN = OBS_SCENE_SHIFT + 15;
    // Scene detail level 0
    public static final int OBS_PYRAMID_SOLID = OBS_SCENE_SHIFT + 2;
    public static final int OBS_PIPE_BODY_RIGHT = OBS_SCENE_SHIFT + 21;
    public static final int OBS_PIPE_BODY_LEFT = OBS_SCENE_SHIFT + 20;
    public static final int OBS_PIPE_TOP_RIGHT = OBS_SCENE_SHIFT + 19;
    public static final int OBS_PIPE_TOP_LEFT = OBS_SCENE_SHIFT + 18;
    public static final int OBS_USED_BLOCK = OBS_SCENE_SHIFT + 14;
    public static final int OBS_BULLET_BILL_BODY = OBS_SCENE_SHIFT + 5;
    public static final int OBS_BULLET_BILL_NECT = OBS_SCENE_SHIFT + 4;
    public static final int OBS_BULLET_BILL_HEAD = OBS_SCENE_SHIFT + 3;
    public static final int OBS_BACKGROUND = OBS_SCENE_SHIFT + 47;
    public static final int OBS_PLATFORM_SINGLE = OBS_SCENE_SHIFT + 43;
    public static final int OBS_PLATFORM_LEFT = OBS_SCENE_SHIFT + 44;
    public static final int OBS_PLATFORM_RIGHT = OBS_SCENE_SHIFT + 45;
    public static final int OBS_PLATFORM_CENTER = OBS_SCENE_SHIFT + 46;
    // Scene detail level 1
    public static final int OBS_PLATFORM = OBS_SCENE_SHIFT + 43;
    public static final int OBS_CANNON = OBS_SCENE_SHIFT + 3;
    public static final int OBS_PIPE = OBS_SCENE_SHIFT + 18;
    // Scene detail level 2
    public static final int OBS_SCENE_OBJECT = OBS_SCENE_SHIFT + 84;

    // Common between enemies detail level 0 and 1
    public static final int OBS_FIREBALL = 16;
    // Enemies Detail 0
    public static final int OBS_GOOMBA = 2;
    public static final int OBS_GOOMBA_WINGED = 3;
    public static final int OBS_RED_KOOPA = 4;
    public static final int OBS_RED_KOOPA_WINGED = 5;
    public static final int OBS_GREEN_KOOPA = 6;
    public static final int OBS_GREEN_KOOPA_WINGED = 7;
    public static final int OBS_SPIKY = 8;
    public static final int OBS_SPIKY_WINGED = 9;
    public static final int OBS_BULLET_BILL = 10;
    public static final int OBS_ENEMY_FLOWER = 11;
    public static final int OBS_MUSHROOM = 12;
    public static final int OBS_FIRE_FLOWER = 13;
    public static final int OBS_SHELL = 14;
    public static final int OBS_LIFE_MUSHROOM = 15;
    // Enemies Detail 1
    public static final int OBS_STOMPABLE_ENEMY = 2;
    public static final int OBS_NONSTOMPABLE_ENEMY = 8;
    public static final int OBS_SPECIAL_ITEM = 12;
    // Enemies Detail 2
    public static final int OBS_ENEMY = 1;

    public static int getSpriteTypeGeneralization(SpriteType sprite, int detail) {
        switch (detail) {
            case (0):
                switch (sprite) {
                    case MARIO:
                        return OBS_NONE;
                    default:
                        sprite.getValue();
                }
            case (1):
                switch (sprite) {
                    case MARIO:
                        return OBS_NONE;
                    case FIREBALL:
                        return OBS_FIREBALL;
                    case MUSHROOM:
                    case LIFE_MUSHROOM:
                    case FIRE_FLOWER:
                        return OBS_SPECIAL_ITEM;
                    case BULLET_BILL:
                    case SHELL:
                    case GOOMBA:
                    case GOOMBA_WINGED:
                    case GREEN_KOOPA:
                    case GREEN_KOOPA_WINGED:
                    case RED_KOOPA:
                    case RED_KOOPA_WINGED:
                        return OBS_STOMPABLE_ENEMY;
                    case SPIKY:
                    case SPIKY_WINGED:
                    case ENEMY_FLOWER:
                        return OBS_NONSTOMPABLE_ENEMY;
                    default:
                        return OBS_NONE;
                }
            case (2):
                switch (sprite) {
                    case FIREBALL:
                    case MARIO:
                    case MUSHROOM:
                    case LIFE_MUSHROOM:
                    case FIRE_FLOWER:
                        return OBS_NONE;
                    default:
                        return OBS_ENEMY;
                }
        }
        return OBS_UNDEF;
    }

    public static int getBlockValueGeneralization(int tile, int detail) {
        if (tile == 0) {
            return OBS_NONE;
        }
        switch (detail) {
            case (0):
                switch (tile) {
                    // invisble blocks
                    case 48:
                    case 49:
                        return OBS_NONE;
                    // brick blocks
                    case 6:
                    case 7:
                    case 50:
                    case 51:
                        return OBS_BRICK;
                    // ? blocks
                    case 8:
                    case 11:
                        return OBS_QUESTION_BLOCK;
                }
                return tile + OBS_SCENE_SHIFT;
            case (1):
                switch (tile) {
                    // invisble blocks
                    case 48:
                    case 49:
                        // body for jumpthrough platform
                    case 47:
                        return OBS_NONE;
                    // solid blocks
                    case 1:
                    case 2:
                    case 14:
                        return OBS_SOLID;
                    // bullet bill blocks
                    case 3:
                    case 4:
                    case 5:
                        return OBS_CANNON;
                    // pipe blocks
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                        return OBS_PIPE;
                    // brick blocks
                    case 6:
                    case 7:
                    case 50:
                    case 51:
                        return OBS_BRICK;
                    // ? blocks
                    case 8:
                    case 11:
                        return OBS_QUESTION_BLOCK;
                    // coin
                    case 15:
                        return OBS_COIN;
                    // Jump through platforms
                    case 44:
                    case 45:
                    case 46:
                        return OBS_PLATFORM;
                }
                return OBS_NONE;
            case (2):
                switch (tile) {
                    // invisible blocks
                    case 48:
                    case 49:
                        // body for jumpthrough platform
                    case 47:
                        return OBS_NONE;
                }
                // everything else is "something", so it is 100
                return OBS_SCENE_OBJECT;
        }
        return OBS_UNDEF;
    }

    /**
     * The width of the observation grid
     */
    public final int obsGridWidth = MarioGame.tileWidth;
    /**
     * The height of the observation grid
     */
    public final int obsGridHeight = MarioGame.tileHeight;

    private MarioWorld world;

    // stats
    private int fallKill;
    private int stompKill;
    private int fireKill;
    private int shellKill;
    private int mushrooms;
    private int flowers;
    private int breakBlock;

    /**
     * Create a forward model object
     *
     * @param world the current level world that is being used. This class hides the
     *              world object so the agents won't cheat.
     */
    public MarioForwardModel(MarioWorld world) {
        this.world = world;
    }

    /**
     * Create a clone from the current forward model state
     *
     * @return a clone from the current forward model state
     */
    public MarioForwardModel clone() {
        MarioForwardModel model = new MarioForwardModel(this.world.clone());
        model.fallKill = this.fallKill;
        model.stompKill = this.stompKill;
        model.fireKill = this.fireKill;
        model.shellKill = this.shellKill;
        model.mushrooms = this.mushrooms;
        model.flowers = this.flowers;
        model.breakBlock = this.breakBlock;
        return model;
    }

    /**
     * Advance the forward model using the action array
     *
     * @param actions a list of all the button states
     */
    public void advance(boolean[] actions) {
        this.world.update(actions);
        for (MarioEvent e : this.world.lastFrameEvents) {
            if (e.getEventType() == EventType.FIRE_KILL.getValue()) {
                this.fireKill += 1;
            }
            if (e.getEventType() == EventType.STOMP_KILL.getValue()) {
                this.stompKill += 1;
            }
            if (e.getEventType() == EventType.FALL_KILL.getValue()) {
                this.fallKill += 1;
            }
            if (e.getEventType() == EventType.SHELL_KILL.getValue()) {
                this.shellKill += 1;
            }
            if (e.getEventType() == EventType.COLLECT.getValue()) {
                if (e.getEventParam() == SpriteType.FIRE_FLOWER.getValue()) {
                    this.flowers += 1;
                }
                if (e.getEventParam() == SpriteType.MUSHROOM.getValue()) {
                    this.mushrooms += 1;
                }
            }
            if (e.getEventType() == EventType.BUMP.getValue() && e.getEventParam() == OBS_BRICK
                    && e.getMarioState() > 0) {
                this.breakBlock += 1;
            }
        }
    }

    /**
     * Get the current state of the running game
     *
     * @return GameStatus the current state (WIN, LOSE, TIME_OUT, RUNNING)
     */
    public GameStatus getGameStatus() {
        return this.world.gameStatus;
    }

    /**
     * The percentage of distance traversed between mario and the goal
     *
     * @return value between 0 to 1 to indicate the percentage of distance traversed
     */
    public float getCompletionPercentage() {
        return this.world.mario.x / (this.world.level.exitTileX * 16);
    }

    /**
     * Get the current level dimensions
     *
     * @return the first value is level width and second is level height
     */
    public float[] getLevelFloatDimensions() {
        return new float[]{this.world.level.width, this.world.level.height};
    }

    /**
     * Get the remaining time before the game timesout
     *
     * @return the number of time ticks before timeout each frame removes 30 frames
     */
    public int getRemainingTime() {
        return this.world.currentTimer;
    }

    /**
     * Get mario position
     *
     * @return the actual mario position in the current state
     */
    public float[] getMarioFloatPos() {
        return new float[]{this.world.mario.x, this.world.mario.y};
    }

    /**
     * Get mario velocity
     *
     * @return the actual mario velocity in the current state
     */
    public float[] getMarioFloatVelocity() {
        return new float[]{this.world.mario.xa, this.world.mario.ya};
    }

    /**
     * If mario can press the jump button while in the air to reach higher areas
     *
     * @return true if the agent can press the button longer and false otherwise
     */
    public boolean getMarioCanJumpHigher() {
        return this.world.mario.jumpTime > 0;
    }

    /**
     * Get the current mario mode
     *
     * @return the current mario mode (0-small, 1-large, 2-fire)
     */
    public int getMarioMode() {
        int value = 0;
        if (this.world.mario.isLarge) {
            value = 1;
        }
        if (this.world.mario.isFire) {
            value = 2;
        }
        return value;
    }

    /**
     * Get to know if mario is touching the ground.
     *
     * @return true if mario is touching the ground and false otherwise
     */
    public boolean isMarioOnGround() {
        return this.world.mario.onGround;
    }

    /**
     * Get to know if mario is able to jump
     *
     * @return true if mario can jump and false otherwise
     */
    public boolean mayMarioJump() {
        return this.world.mario.mayJump;
    }

    /**
     * Get a 3x float list that contain the type of enemies, x position, y position
     *
     * @return an array of 3 floats that contain the enemy type, x position, y
     * position for each enemy sprite
     */
    public float[] getEnemiesFloatPos() {
        ArrayList<MarioSprite> enemiesAlive = this.world.getEnemies();
        float[] enemyPos = new float[enemiesAlive.size() * 3];
        for (int i = 0; i < enemiesAlive.size(); i++) {
            enemyPos[3 * i] = enemiesAlive.get(i).type.getValue();
            enemyPos[3 * i + 1] = enemiesAlive.get(i).x;
            enemyPos[3 * i + 2] = enemiesAlive.get(i).y;
        }
        return enemyPos;
    }

    /**
     * get the number of enemies killed in the game
     *
     * @return number of enemies killed in the game
     */
    public int getKillsTotal() {
        return this.fallKill + this.fireKill + this.shellKill + this.stompKill;
    }

    /**
     * get the number of enemies killed by fireballs
     *
     * @return number of enemies killed by fireballs
     */
    public int getKillsByFire() {
        return this.fireKill;
    }

    /**
     * get the number of enemies killed by stomping
     *
     * @return number of enemies killed by stomping
     */
    public int getKillsByStomp() {
        return this.stompKill;
    }

    /**
     * get the number of enemies killed by a koopa shell
     *
     * @return number of enemies killed by a koopa shell
     */
    public int getKillsByShell() {
        return this.shellKill;
    }

    /**
     * get the number of enemies that fell from the game screen
     *
     * @return the number of enemies that fell from the game screen
     */
    public int getKillsByFall() {
        return this.fallKill;
    }

    /**
     * get the number 100 coins collected by mario
     *
     * @return number of 100 coins collected by mario
     */
    public int getNumLives() {
        return this.world.lives;
    }

    /**
     * get the number of mushroom collected by mario
     *
     * @return the number of collected mushrooms by mario
     */
    public int getNumCollectedMushrooms() {
        return this.mushrooms;
    }

    /**
     * get the number of fire flowers collected by mario
     *
     * @return the number of collected fire flowers by mario
     */
    public int getNumCollectedFireflower() {
        return this.flowers;
    }

    /**
     * get the number of coins collected by mario
     *
     * @return the number of collected coins by mario
     */
    public int getNumCollectedCoins() {
        return this.world.coins;
    }

    /**
     * get the number of destroyed bricks by large or fire mario
     *
     * @return the number of destroyed bricks by large or fire mario
     */
    public int getNumDestroyedBricks() {
        return this.breakBlock;
    }

    /**
     * Get the tile location of mario with respect to the screen
     *
     * @return the x and y location of mario on the screen as tile values
     */
    public int[] getMarioScreenTilePos() {
        return new int[]{(int) ((this.world.mario.x - this.world.cameraX) / 16), (int) (this.world.mario.y / 16)};
    }

    /**
     * The current screen status as a 2D tile grid around the center of screen with
     * scene detail value 1 and enemy detail value of 0
     *
     * @return 2D grid that have all the information about all objects on the screen
     */
    public int[][] getScreenCompleteObservation() {
        return this.getScreenCompleteObservation(1, 0);
    }

    /**
     * The current enemies on the screen as a 2D tile grid around the center of
     * screen with a detail value of 0
     *
     * @return 2D grid where each tile contain either 0 to indicate no enemy or a
     * number to indicate a certain enemy. Look at SpriteTypes for enemy
     * values (Detail 0).
     */
    public int[][] getScreenEnemiesObservation() {
        return this.getScreenEnemiesObservation(0);
    }

    /**
     * The current objects (not enemies) on the screen as a 2D tile grid around the
     * center of screen with a detail value of 1
     *
     * @return 2D grid where each tile contain either 0 which means it is empty or a
     * value that reflect the type of the tile in that area. Look at
     * TileTypes for the meaning of values (Detail 1)
     */
    public int[][] getScreenSceneObservation() {
        return this.getScreenSceneObservation(1);
    }

    /**
     * The current screen status as a 2D tile grid around mario with scene detail
     * value 1 and enemy detail value of 0
     *
     * @return 2D grid that have all the information about all objects on the screen
     */
    public int[][] getMarioCompleteObservation() {
        return this.getMarioCompleteObservation(1, 0);
    }

    /**
     * The current enemies on the screen as a 2D tile grid around mario with a
     * detail value of 0
     *
     * @return 2D grid where each tile contain either 0 to indicate no enemy or a
     * number to indicate a certain enemy. Look at SpriteTypes for enemy
     * values (Detail 0).
     */
    public int[][] getMarioEnemiesObservation() {
        return this.getMarioEnemiesObservation(0);
    }

    /**
     * The current objects (not enemies) on the screen as a 2D tile grid around
     * mario with a detail value of 1
     *
     * @return 2D grid where each tile contain either 0 which means it is empty or a
     * value that reflect the type of the tile in that area. Look at
     * TileTypes for the meaning of values (Detail 1)
     */
    public int[][] getMarioSceneObservation() {
        return this.getMarioSceneObservation(1);
    }

    /**
     * The current screen status as a 2D tile grid around the center of screen
     *
     * @param sceneDetail the detail level of the scene: 0 all detail, 1 less detailed, 2
     *                    binary detail
     * @param enemyDetail the detail level of the current enemies: 0 all details, 1 less
     *                    detailed, 2 binary detail
     * @return 2D grid that have all the information about all objects on the screen
     */
    public int[][] getScreenCompleteObservation(int sceneDetail, int enemyDetail) {
        return this.world.getMergedObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2,
                sceneDetail, enemyDetail);
    }

    /**
     * The current enemies on the screen as a 2D tile grid around the center of
     * screen
     *
     * @param detail the detail level of the current enemies: 0 all details, 1 less
     *               detailed, 2 binary detail
     * @return 2D grid where each tile contain either 0 to indicate no enemy or a
     * number to indicate a certain enemy. Look at SpriteTypes for enemy
     * values (Detail 0).
     */
    public int[][] getScreenEnemiesObservation(int detail) {
        return this.world.getEnemiesObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2, detail);
    }

    /**
     * The current objects (not enemies) on the screen as a 2D tile grid around the
     * center of screen
     *
     * @param detail the detail level of the scene: 0 all detail, 1 less detailed, 2
     *               binary detail
     * @return 2D grid where each tile contain either 0 which means it is empty or a
     * value that reflect the type of the tile in that area. Look at
     * TileTypes for the meaning of values (Detail 1)
     */
    public int[][] getScreenSceneObservation(int detail) {
        return this.world.getSceneObservation(this.world.cameraX + MarioGame.width / 2, MarioGame.height / 2, detail);
    }

    /**
     * The current screen status as a 2D tile grid around mario
     *
     * @param sceneDetail the detail level of the scene: 0 all detail, 1 less detailed, 2
     *                    binary detail
     * @param enemyDetail the detail level of the current enemies: 0 all details, 1 less
     *                    detailed, 2 binary detail
     * @return 2D grid that have all the information about all objects on the screen
     */
    public int[][] getMarioCompleteObservation(int sceneDetail, int enemyDetail) {
        return this.world.getMergedObservation(this.world.mario.x, this.world.mario.y, sceneDetail, enemyDetail);
    }

    /**
     * The current enemies on the screen as a 2D tile grid around mario
     *
     * @param detail the detail level of the current enemies: 0 all details, 1 less
     *               detailed, 2 binary detail
     * @return 2D grid where each tile contain either 0 to indicate no enemy or a
     * number to indicate a certain enemy. Look at SpriteTypes for enemy
     * values (Detail 0).
     */
    public int[][] getMarioEnemiesObservation(int detail) {
        return this.world.getEnemiesObservation(this.world.mario.x, this.world.mario.y, detail);
    }

    /**
     * The current objects (not enemies) on the screen as a 2D tile grid around
     * mario
     *
     * @param detail the detail level of the scene: 0 all detail, 1 less detailed, 2
     *               binary detail
     * @return 2D grid where each tile contain either 0 which means it is empty or a
     * value that reflect the type of the tile in that area. Look at
     * TileTypes for the meaning of values (Detail 1)
     */
    public int[][] getMarioSceneObservation(int detail) {
        return this.world.getSceneObservation(this.world.mario.x, this.world.mario.y, detail);
    }
}
