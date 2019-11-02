package levelGenerators.JariwalaLuccaGenerator;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

public class LevelGenerator implements MarioLevelGenerator {

    // Hand-made level chunks
    // Each occupies a rectangular area of the map positioned along the
    // bottom of the map with air above it
    final String LO_GROUND = "" +
            "XXXXX" + "\n" +
            "XXXXX" + "\n";
    final String HI_GROUND = "" +
            "XXXX" + "\n" +
            "XXXX" + "\n" +
            "XXXX" + "\n" +
            "XXXX" + "\n" +
            "XXXX";
    final String PLAT = "-XXXXX-\n\n\n\n\n\n";
    final String GAP = "---";

    final String[] levelChunks = {
            LO_GROUND,
            HI_GROUND,
            PLAT,
            GAP
    };

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        return null;
    }

    @Override
    public String getGeneratorName() {
        return "JariwalaLuccaGenerator";
    }

}
