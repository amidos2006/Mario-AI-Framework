package levelGenerators.JariwalaLuccaGenerator;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

public class LevelGenerator implements MarioLevelGenerator {

    // Hand-made level chunks
    // Each occupies a rectangular area positioned along the bottom of the map
    // with air above it.

    /*
        X = ground
        S = Brick
        ! = coin power up block
        g = goomba
        k = koopa (green)
        r = koppa (red)
        R = koppa (red + flying)
        @ = power up
        t = pipe normal
        T = pipe with pirana plant
        # = block
        1 = ???
        M = mario
        F = Flag
        C = multi hit coin block?
        % = mushroom floating platform
        o = coin
*/

    private final String LO_GROUND = "" +
            "XXXXX" + "\n" +
            "XXXXX";

    private final String HI_GROUND = "" +
            "XXXX" + "\n" +
            "XXXX" + "\n" +
            "XXXX" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    private final String PLAT = "" +
            "-XXXXX-" + "\n" +
            "-------" + "\n" +
            "-------" + "\n" +
            "-------" + "\n" +
            "-------";

    private final String GAP = "---";

    private final String HILL = "" +
            "----#--#----" + "\n" +
            "---##--##---" + "\n" +
            "--###--###--" + "\n" +
            "-####--####-" + "\n" +
            "#####--#####";

    private final String FLAG = "" +
            "-" + "\n" +
            "F" + "\n" +
            "#" + "\n" +
            "X" + "\n" +
            "X";

    private final String START = "" +
            "---" + "\n" +
            "---" + "\n" +
            "-M-" + "\n" +
            "XXX" + "\n" +
            "XXX";

    private final String LOWPIPE = "" +
            "----" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    private final String HIGHPIPE = "" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "-tt-" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    private final String LOWPIPEPLANT = "" +
            "----" + "\n" +
            "-TT-" + "\n" +
            "-TT-" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    private final String HIGHPIPEPLANT = "" +
            "-TT-" + "\n" +
            "-TT-" + "\n" +
            "-TT-" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    private final String GOOMBA1 = "" +
            "-------" + "\n" +
            "-------" + "\n" +
            "-g--g--" + "\n" +
            "XXXXXXX" + "\n" +
            "XXXXXXX";

    private final String GOOMBA2 = "" +
            "--SSS--" + "\n" +
            "-------" + "\n" +
            "-g-g-g-" + "\n" +
            "XXXXXXX" + "\n" +
            "XXXXXXX";

    private final String KOOPA1 = "" +
            "-------" + "\n" +
            "-------" + "\n" +
            "-r-g-g-" + "\n" +
            "XXXXXXX" + "\n" +
            "XXXXXXX";

    private final String KOOPA2 = "" +
            "-------" + "\n" +
            "-------" + "\n" +
            "--k-k--" + "\n" +
            "XXXXXXX" + "\n" +
            "XXXXXXX";

    private final String KOOPA3 = "" +
            "---R---" + "\n" +
            "--SSS--" + "\n" +
            "-------" + "\n" +
            "XXXXXXX" + "\n" +
            "XXXXXXX";

    private final String MIX1 = "" +
            "----------" + "\n" +
            "----------" + "\n" +
            "----------" + "\n" +
            "XXXXXXXXXX" + "\n" +
            "XXXXXXXXXX";

    private final String[] LEVEL_CHUNKS = {
            LO_GROUND,
            HI_GROUND,
            PLAT,
            GAP
    };

    // The level model for this level
    private MarioLevelModel marioLevelModel;

    // The next x-coordinate to write to during generation
    private int cursorPos = 0;

    /**
     * Get the height of the given chunk
     * @param chunk The level chunk to measure
     * @return The height (how tall in blocks) of the chunk
     */
    private int getChunkHeight(String chunk) {
        int numLines = 1;

        // Iterate over chunk, one char at a time
        for (char c : chunk.toCharArray()) {
            if (c == '\n') {
                numLines++;
            }
        }

        return numLines;
    }

    /**
     * Iterates through the characters of the string chunk and add them as
     * blocks/enemies to the level model's map
     * @param chunk The string that describes a chunk of blocks to add
     */
    private void addChunkToMap(String chunk) {
        // Coords to write character to in map
        int x = cursorPos;
        int y = marioLevelModel.getHeight() - getChunkHeight(chunk);

        // Iterate over chunk, one char at a time
        for (char block : chunk.toCharArray()) {
            // Go to next row in level map if newline, else write block and go
            // to next column in level map
            if (block == '\n') {
                y++;
                x = cursorPos;
            } else {
                marioLevelModel.setBlock(x, y, block);
                x++;
            }
        }

        // Update generation cursor position
        cursorPos = x;
    }

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        // Store the given model so other methods have access
        this.marioLevelModel = model;

        // Set everything in the map to empty
        model.setRectangle(0, 0, model.getWidth(), model.getHeight(), MarioLevelModel.EMPTY);

        // Test adding chunks
        for (int i = 0; i < 2; i++) {
            for (String chunk : LEVEL_CHUNKS) {
                addChunkToMap(chunk);
            }
        }

        System.out.println(model.getMap());

        return null;
    }

    @Override
    public String getGeneratorName() {
        return "JariwalaLuccaGenerator";
    }

}
