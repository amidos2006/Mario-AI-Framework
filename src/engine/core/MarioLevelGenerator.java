package engine.core;

public interface MarioLevelGenerator {
    /**
     * Generate a playable mario level
     * @param levelWidth the width of the level in tiles
     * @param timer maximum time that the generator can run under
     * @return a playable mario level
     */
    String getGeneratedLevel(int levelWidth, int levelHeight, MarioTimer timer);
    
    /**
     * Return the name of the level generator
     * @return the name of the level generator
     */
    String getGeneratorName();
}
