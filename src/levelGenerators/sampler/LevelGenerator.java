package levelGenerators.sampler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

public class LevelGenerator implements MarioLevelGenerator{
    private int sampleWidth = 10;
    private String folderName = "levels/original/";
    
    private Random rnd;
    private char[][] map;
    
    public LevelGenerator() {
	this("levels/original/", 10);
    }
    
    public LevelGenerator(String sampleFolder) {
	this(sampleFolder, 10);
    }
    
    public LevelGenerator(String sampleFolder, int sampleWidth) {
	this.sampleWidth = sampleWidth;
	this.folderName = sampleFolder;
    }
    
    private char[][] getRandomSample(int index) throws IOException{
	File[] listOfFiles = new File(folderName).listFiles();
	List<String> lines = Files.readAllLines(listOfFiles[rnd.nextInt(listOfFiles.length)].toPath());
	char[][] sample = MarioLevelModel.createEmptyMap(sampleWidth, this.map[0].length);
	for(int y=0; y<lines.size(); y++) {
	    for(int x=0; x<sampleWidth; x++) {
		sample[x][y] = lines.get(y).charAt(index * sampleWidth + x);
	    }
	}
	return sample;
    }
    
    @Override
    public String getGeneratedLevel(int levelWidth, int levelHeight, MarioTimer timer) {
	rnd = new Random();
	map = MarioLevelModel.createEmptyMap(levelWidth, levelHeight);
	for(int i=0; i<levelWidth / sampleWidth; i++){
	    try {
		char[][] sample = this.getRandomSample(i);
		for(int x=0; x<sample.length; x++) {
		    for(int y=0; y<sample[x].length; y++) {
			map[x+i*sampleWidth][y] = sample[x][y];
		    }
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return MarioLevelModel.createStringMap(map);
    }

    @Override
    public String getGeneratorName() {
	return "SamplerLevelGenerator";
    }
}
