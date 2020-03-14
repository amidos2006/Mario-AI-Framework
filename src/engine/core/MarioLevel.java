package engine.core;

import java.awt.Graphics;
import java.util.ArrayList;

import engine.graphics.MarioImage;
import engine.graphics.MarioTilemap;
import engine.helper.Assets;
import engine.helper.SpriteType;
import engine.helper.TileFeature;

public class MarioLevel {

    public int width = MarioGame.width;
    public int tileWidth = MarioGame.width / 16;
    public int height = MarioGame.height;
    public int tileHeight = MarioGame.height / 16;
    public int totalCoins = 0;
    public int marioTileX, marioTileY, exitTileX, exitTileY;

    private int[][] levelTiles;
    private SpriteType[][] spriteTemplates;
    private int[][] lastSpawnTime;
    private MarioTilemap graphics;
    private MarioImage flag;

    public MarioLevel(String level, boolean visuals) {
        if (level.trim().length() == 0) {
            this.tileWidth = 0;
            this.width = 0;
            this.tileHeight = 0;
            this.height = 0;
            return;
        }
        String[] lines = level.split("\\r?\\n");
        this.tileWidth = lines[0].length();
        this.width = this.tileWidth * 16;
        this.tileHeight = lines.length;
        this.height = this.tileHeight * 16;

        this.levelTiles = new int[lines[0].length()][lines.length];
        this.spriteTemplates = new SpriteType[lines[0].length()][lines.length];
        this.lastSpawnTime = new int[lines[0].length()][lines.length];
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                this.levelTiles[x][y] = 0;
                this.spriteTemplates[x][y] = SpriteType.NONE;
                this.lastSpawnTime[x][y] = -40;
            }
        }

        boolean marioLocInit = false;
        boolean exitLocInit = false;
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                Character c = lines[y].charAt(x);
                switch (c) {
                    case 'M':
                        this.marioTileX = x;
                        this.marioTileY = y;
                        marioLocInit = true;
                        break;
                    case 'F':
                        this.exitTileX = x;
                        this.exitTileY = y;
                        exitLocInit = true;
                        break;
                    case 'y':
                        this.spriteTemplates[x][y] = SpriteType.SPIKY;
                        break;
                    case 'Y':
                        this.spriteTemplates[x][y] = SpriteType.SPIKY_WINGED;
                        break;
                    case 'E':
                    case 'g':
                        this.spriteTemplates[x][y] = SpriteType.GOOMBA;
                        break;
                    case 'G':
                        this.spriteTemplates[x][y] = SpriteType.GOOMBA_WINGED;
                        break;
                    case 'k':
                        this.spriteTemplates[x][y] = SpriteType.GREEN_KOOPA;
                        break;
                    case 'K':
                        this.spriteTemplates[x][y] = SpriteType.GREEN_KOOPA_WINGED;
                        break;
                    case 'r':
                        this.spriteTemplates[x][y] = SpriteType.RED_KOOPA;
                        break;
                    case 'R':
                        this.spriteTemplates[x][y] = SpriteType.RED_KOOPA_WINGED;
                        break;
                    case 'X':
                        //floor
                        this.levelTiles[x][y] = 1;
                        break;
                    case '#':
                        //pyramidBlock
                        this.levelTiles[x][y] = 2;
                        break;
                    case '%':
                        //jump through block
                        int tempIndex = 0;
                        if (x > 0 && lines[y].charAt(x - 1) == '%') {
                            tempIndex += 2;
                        }
                        if (x < this.levelTiles.length - 1 && lines[y].charAt(x + 1) == '%') {
                            tempIndex += 1;
                        }
                        this.levelTiles[x][y] = 43 + tempIndex;
                        break;
                    case '|':
                        //background for jump through block
                        this.levelTiles[x][y] = 47;
                        break;
                    case '*':
                        //bullet bill
                        tempIndex = 0;
                        if (y > 0 && lines[y - 1].charAt(x) == '*') {
                            tempIndex += 1;
                        }
                        if (y > 1 && lines[y - 2].charAt(x) == '*') {
                            tempIndex += 1;
                        }
                        this.levelTiles[x][y] = 3 + tempIndex;
                        break;
                    case 'B':
                        //bullet bill head
                        this.levelTiles[x][y] = 3;
                        break;
                    case 'b':
                        //bullet bill neck and body
                        tempIndex = 0;
                        if (y > 1 && lines[y - 2].charAt(x) == 'B') {
                            tempIndex += 1;
                        }
                        this.levelTiles[x][y] = 4 + tempIndex;
                        break;
                    case '?':
                    case '@':
                        //mushroom question block
                        this.levelTiles[x][y] = 8;
                        break;
                    case 'Q':
                    case '!':
                        //coin question block
                        this.totalCoins += 1;
                        this.levelTiles[x][y] = 11;
                        break;
                    case '1':
                        //invisible 1 up block
                        this.levelTiles[x][y] = 48;
                        break;
                    case '2':
                        //invisible coin block
                        this.totalCoins += 1;
                        this.levelTiles[x][y] = 49;
                        break;
                    case 'D':
                        //used
                        this.levelTiles[x][y] = 14;
                        break;
                    case 'S':
                        //normal block
                        this.levelTiles[x][y] = 6;
                        break;
                    case 'C':
                        //coin block
                        this.totalCoins += 1;
                        this.levelTiles[x][y] = 7;
                        break;
                    case 'U':
                        //mushroom block
                        this.levelTiles[x][y] = 50;
                        break;
                    case 'L':
                        //1up block
                        this.levelTiles[x][y] = 51;
                        break;
                    case 'o':
                        //coin
                        this.totalCoins += 1;
                        this.levelTiles[x][y] = 15;
                        break;
                    case 't':
                        //empty Pipe
                        tempIndex = 0;
                        boolean singlePipe = false;
                        if (x < lines[y].length() - 1 && Character.toLowerCase(lines[y].charAt(x + 1)) != 't' &&
                                x > 0 && Character.toLowerCase(lines[y].charAt(x - 1)) != 't') {
                            singlePipe = true;
                        }
                        if (x > 0 && (this.levelTiles[x - 1][y] == 18 || this.levelTiles[x - 1][y] == 20)) {
                            tempIndex += 1;
                        }
                        if (y > 0 && Character.toLowerCase(lines[y - 1].charAt(x)) == 't') {
                            if (singlePipe) {
                                tempIndex += 1;
                            } else {
                                tempIndex += 2;
                            }
                        }
                        if (singlePipe) {
                            this.levelTiles[x][y] = 52 + tempIndex;
                        } else {
                            this.levelTiles[x][y] = 18 + tempIndex;
                        }
                        break;
                    case 'T':
                        //flower pipe
                        tempIndex = 0;
                        singlePipe = x < lines[y].length() - 1 && Character.toLowerCase(lines[y].charAt(x + 1)) != 't' &&
                                x > 0 && Character.toLowerCase(lines[y].charAt(x - 1)) != 't';
                        if (x > 0 && (this.levelTiles[x - 1][y] == 18 || this.levelTiles[x - 1][y] == 20)) {
                            tempIndex += 1;
                        }
                        if (y > 0 && Character.toLowerCase(lines[y - 1].charAt(x)) == 't') {
                            if (singlePipe) {
                                tempIndex += 1;
                            } else {
                                tempIndex += 2;
                            }
                        }
                        if (singlePipe) {
                            this.levelTiles[x][y] = 52 + tempIndex;
                        } else {
                            if (tempIndex == 0) {
                                this.spriteTemplates[x][y] = SpriteType.ENEMY_FLOWER;
                            }
                            this.levelTiles[x][y] = 18 + tempIndex;
                        }
                        break;
                    case '<':
                        //pipe top left
                        this.levelTiles[x][y] = 18;
                        break;
                    case '>':
                        //pipe top right
                        this.levelTiles[x][y] = 19;
                        break;
                    case '[':
                        //pipe body left
                        this.levelTiles[x][y] = 20;
                        break;
                    case ']':
                        //pipe body right
                        this.levelTiles[x][y] = 21;
                        break;
                }
            }
        }
        if (!marioLocInit) {
            this.marioTileX = 0;
            this.marioTileY = findFirstFloor(lines, this.marioTileX);
        }
        if (!exitLocInit) {
            this.exitTileX = lines[0].length() - 1;
            this.exitTileY = findFirstFloor(lines, this.exitTileX);
        }
        for (int y = this.exitTileY; y > Math.max(1, this.exitTileY - 11); y--) {
            this.levelTiles[this.exitTileX][y] = 40;
        }
        this.levelTiles[this.exitTileX][Math.max(1, this.exitTileY - 11)] = 39;

        if (visuals) {
            this.graphics = new MarioTilemap(Assets.level, this.levelTiles);
            this.flag = new MarioImage(Assets.level, 41);
            this.flag.width = 16;
            this.flag.height = 16;
        }
    }

    public MarioLevel clone() {
        MarioLevel level = new MarioLevel("", false);
        level.width = this.width;
        level.height = this.height;
        level.tileWidth = this.tileWidth;
        level.tileHeight = this.tileHeight;
        level.totalCoins = this.totalCoins;
        level.marioTileX = this.marioTileX;
        level.marioTileY = this.marioTileY;
        level.exitTileX = this.exitTileX;
        level.exitTileY = this.exitTileY;
        level.levelTiles = new int[this.levelTiles.length][this.levelTiles[0].length];
        level.lastSpawnTime = new int[this.levelTiles.length][this.levelTiles[0].length];
        for (int x = 0; x < level.levelTiles.length; x++) {
            for (int y = 0; y < level.levelTiles[x].length; y++) {
                level.levelTiles[x][y] = this.levelTiles[x][y];
                level.lastSpawnTime[x][y] = this.lastSpawnTime[x][y];
            }
        }
        level.spriteTemplates = this.spriteTemplates;
        return level;
    }

    public boolean isBlocking(int xTile, int yTile, float xa, float ya) {
        int block = this.getBlock(xTile, yTile);
        ArrayList<TileFeature> features = TileFeature.getTileType(block);
        boolean blocking = features.contains(TileFeature.BLOCK_ALL);
        blocking |= (ya < 0) && features.contains(TileFeature.BLOCK_UPPER);
        blocking |= (ya > 0) && features.contains(TileFeature.BLOCK_LOWER);

        return blocking;
    }

    public int getBlock(int xTile, int yTile) {
        if (xTile < 0) {
            xTile = 0;
        }
        if (xTile > this.tileWidth - 1) {
            xTile = this.tileWidth - 1;
        }
        if (yTile < 0 || yTile > this.tileHeight - 1) {
            return 0;
        }
        return this.levelTiles[xTile][yTile];
    }

    public void setBlock(int xTile, int yTile, int index) {
        if (xTile < 0 || yTile < 0 || xTile > this.tileWidth - 1 || yTile > this.tileHeight - 1) {
            return;
        }
        this.levelTiles[xTile][yTile] = index;
    }

    public void setShiftIndex(int xTile, int yTile, int shift) {
        if (this.graphics == null || xTile < 0 || yTile < 0 || xTile > this.tileWidth - 1 || yTile > this.tileHeight - 1) {
            return;
        }
        this.graphics.moveShift[xTile][yTile] = shift;
    }

    public SpriteType getSpriteType(int xTile, int yTile) {
        if (xTile < 0 || yTile < 0 || xTile >= this.tileWidth || yTile >= this.tileHeight) {
            return SpriteType.NONE;
        }
        return this.spriteTemplates[xTile][yTile];
    }

    public int getLastSpawnTick(int xTile, int yTile) {
        if (xTile < 0 || yTile < 0 || xTile > this.tileWidth - 1 || yTile > this.tileHeight - 1) {
            return 0;
        }
        return this.lastSpawnTime[xTile][yTile];
    }

    public void setLastSpawnTick(int xTile, int yTile, int tick) {
        if (xTile < 0 || yTile < 0 || xTile > this.tileWidth - 1 || yTile > this.tileHeight - 1) {
            return;
        }
        this.lastSpawnTime[xTile][yTile] = tick;
    }

    public String getSpriteCode(int xTile, int yTile) {
        return xTile + "_" + yTile + "_" + this.getSpriteType(xTile, yTile).getValue();
    }

    private boolean isSolid(char c) {
        return c == 'X' || c == '#' || c == '@' || c == '!' || c == 'B' || c == 'C' ||
                c == 'Q' || c == '<' || c == '>' || c == '[' || c == ']' || c == '?' ||
                c == 'S' || c == 'U' || c == 'D' || c == '%' || c == 't' || c == 'T';
    }

    private int findFirstFloor(String[] lines, int x) {
        boolean skipLines = true;
        for (int i = lines.length - 1; i >= 0; i--) {
            Character c = lines[i].charAt(x);
            if (isSolid(c)) {
                skipLines = false;
                continue;
            }
            if (!skipLines && !isSolid(c)) {
                return i;
            }
        }
        return -1;
    }

    public void update(int cameraX, int cameraY) {

    }

    public void render(Graphics og, int cameraX, int cameraY) {
        this.graphics.render(og, cameraX, cameraY);
        if (cameraX + MarioGame.width >= this.exitTileX * 16) {
            this.flag.render(og, this.exitTileX * 16 - 8 - cameraX, Math.max(1, this.exitTileY - 11) * 16 + 16 - cameraY);
        }
    }
}
