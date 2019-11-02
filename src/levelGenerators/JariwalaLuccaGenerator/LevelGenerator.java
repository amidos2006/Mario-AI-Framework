package levelGenerators.JariwalaLuccaGenerator;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

public class LevelGenerator implements MarioLevelGenerator {

    // Hand-made level chunks
    // Each occupies a rectangular area of the map positioned along the
    // bottom of the map with air above it
    private final String LO_GROUND = "" +
            "XXXXX" + "\n" +
            "XXXXX" + "\n";

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
        int numLines = 0;

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

        return null;
    }

    @Override
    public String getGeneratorName() {
        return "JariwalaLuccaGenerator";
    }

}
