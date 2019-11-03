package levelGenerators.JariwalaLuccaGenerator;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

import java.util.HashMap;

public class LevelGenerator implements MarioLevelGenerator {

    private double LO_GROUND_CHANCE = 0.0;
    private double HI_GROUND_CHANCE = 0.0;
    private double PLAT_CHANCE = 0.1;
    private double GAP_CHANCE = 0.2;
    private double HILL_CHANCE = 0.1;
    private double LOWPIPE_CHANCE = 0.2;
    private double HIGHPIPE_CHANCE = 0.2;
    private double LOWPIPEPLANT_CHANCE = 0.1;
    private double HIGHPIPEPLANT_CHANCE = 0.1;
    private double GOOBA1_CHANCE = 0.2;
    private double GOOBA2_CHANCE = 0.2;
    private double KOOPA1_CHANCE = 0.2;
    private double KOOPA2_CHANCE = 0.2;
    private double KOOPA3_CHANCE = 0.2;
    private double MIX1_CHANCE = 0.2;
    private double MIX2_CHANCE = 0.2;
    private double EMPTY_CHANCE = 0.2;
    private double RAMP_CHANCE = 0.1;
    private double START_CHANCE = 0.0;


    HashMap<String, Double> inner = new HashMap<String, Double>();
    HashMap<String, HashMap> outer = new HashMap<String, HashMap>();



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
            "----" + "\n" +
            "XXXX" + "\n" +
            "XXXX" + "\n" +
            "XXXX" + "\n" +
            "XXXX";

    private final String PLAT = "" +
            "-SSSSS-" + "\n" +
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
            "---" + "\n" +
            "-F-" + "\n" +
            "-#-" + "\n" +
            "XXX" + "\n" +
            "XXX";

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
            "---!--R---" + "\n" +
            "-SS---SS--" + "\n" +
            "-----r---o" + "\n" +
            "XXXXXXXXXX" + "\n" +
            "XXXXXXXXXX";

    private final String MIX2 = "" +
            "----------" + "\n" +
            "-oo------!" + "\n" +
            "--g-----g-" + "\n" +
            "XXX-%%-XXX" + "\n" +
            "XXX----XXX";

    private final String EMPTY = "" +
            "----------" + "\n" +
            "----------" + "\n" +
            "----------" + "\n" +
            "XXXXXXXXXX" + "\n" +
            "XXXXXXXXXX";

    private final String RAMP = "" +
            "--------##" + "\n" +
            "-------###" + "\n" +
            "------####" + "\n" +
            "XXXXXXXXXX" + "\n" +
            "XXXXXXXXXX";

    private final String[] LEVEL_CHUNKS = {
            LO_GROUND,
            HI_GROUND,
            PLAT,
            GAP,
            HILL,
            LOWPIPE,
            HIGHPIPE,
            LOWPIPEPLANT,
            HIGHPIPEPLANT,
            GOOMBA1,
            GOOMBA2,
            KOOPA1,
            KOOPA2,
            KOOPA3,
            MIX1,
            MIX2,
            EMPTY,
            RAMP,
            START
    };

    private final Double[] LEVEL_CHUNKS_VALUES = {
        LO_GROUND_CHANCE,
        HI_GROUND_CHANCE,
        PLAT_CHANCE,
        GAP_CHANCE,
        HILL_CHANCE,
        LOWPIPE_CHANCE,
        HIGHPIPE_CHANCE,
        LOWPIPEPLANT_CHANCE,
        HIGHPIPEPLANT_CHANCE,
        GOOBA1_CHANCE,
        GOOBA2_CHANCE,
        KOOPA1_CHANCE,
        KOOPA2_CHANCE,
        KOOPA3_CHANCE,
        MIX1_CHANCE,
        MIX2_CHANCE,
        EMPTY_CHANCE,
        RAMP_CHANCE,
        START_CHANCE
    };

    public void createHash() {
        for (int i = 0; i < LEVEL_CHUNKS.length; i++) {
            inner.put(LEVEL_CHUNKS[i], LEVEL_CHUNKS_VALUES[i]);
        }
        for (int i = 0; i < LEVEL_CHUNKS.length; i++) {
            outer.put(LEVEL_CHUNKS[i], inner);
        }
    }

    // The level model for this level
    private MarioLevelModel marioLevelModel;

    // The next x-coordinate to write to during generation
    private int cursorPos = 0;

    private String getNextChunk(String lastChunk) {
        // Map of weights of next chunks
        HashMap<String, Double> weights = outer.get(lastChunk);

        // Get total weight of all choices
        double weightSum = 0;
        for (Double weight : weights.values()) {
            weightSum += weight;
        }

        // Pick a random number [0, weightSum)
        double rand = weightSum * Math.random();
        double cumulativeWeight = 0;

        // Select chunk based on random value
        for (String chunk : weights.keySet()) {
            // Accumulate weight of next chunk
            cumulativeWeight += weights.get(chunk);

            if (rand <= cumulativeWeight) {
                // Found randomly selected chunk
                return chunk;
            }
        }

        // Shouldn't get here unless something went wrong in the accumulate weight loop
        return null;
    }

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
        createHash();
        // Store the given model so other methods have access
        this.marioLevelModel = model;
        String currentChunk = START;

        // Set everything in the map to empty
        model.setRectangle(0, 0, model.getWidth(), model.getHeight(), MarioLevelModel.EMPTY);

        addChunkToMap(currentChunk);

        while (cursorPos < model.getWidth()-6) {
            currentChunk = getNextChunk(currentChunk);
            addChunkToMap(currentChunk);
        }

        addChunkToMap(FLAG);

        System.out.println(model.getMap());

        return model.getMap();
    }

    @Override
    public String getGeneratorName() {
        return "JariwalaLuccaGenerator";
    }

}
