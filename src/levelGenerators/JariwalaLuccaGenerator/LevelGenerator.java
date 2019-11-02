package levelGenerators.JariwalaLuccaGenerator;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

import java.util.ArrayList;

public class LevelGenerator implements MarioLevelGenerator {

    ArrayList<String> levelChunks = new ArrayList<>();

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        return null;
    }

    @Override
    public String getGeneratorName() {
        return "JariwalaLuccaGenerator";
    }

}
