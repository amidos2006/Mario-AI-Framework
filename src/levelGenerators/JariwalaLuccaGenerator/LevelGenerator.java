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
    private final String PLAT = "-XXXXX-\n\n\n\n\n\n";
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
     * Iterates through the characters of the string chunk and add them as
     * blocks/enemies to the level model's map
     * @param chunk The string that describes a chunk of blocks to add
     */
    private void addChunkToMap(String chunk) {
        int x = 0;
        int y = 0;

    }

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        this.marioLevelModel = model;

        return null;
    }

    @Override
    public String getGeneratorName() {
        return "JariwalaLuccaGenerator";
    }

}
