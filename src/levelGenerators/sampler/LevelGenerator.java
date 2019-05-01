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
    
    private char[][] getRandomSample(int index, int levelHeight) throws IOException{
	File[] listOfFiles = new File(folderName).listFiles();
	List<String> lines = Files.readAllLines(listOfFiles[rnd.nextInt(listOfFiles.length)].toPath());
	char[][] sample = new char[sampleWidth][levelHeight];
	for(int y=0; y<lines.size(); y++) {
	    for(int x=0; x<sampleWidth; x++) {
		int width = lines.get(y).length();
		sample[x][y] = lines.get(y).charAt(Math.min(index * sampleWidth + x, width - 1));
	    }
	}
	return sample;
    }
    
    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
	rnd = new Random();
	model.clearMap();
	for(int i=0; i<model.getWidth() / sampleWidth; i++){
	    try {
		char[][] sample = this.getRandomSample(i, model.getHeight());
		for(int x=0; x<sample.length; x++) {
		    for(int y=0; y<sample[x].length; y++) {
			model.setBlock(x + i*sampleWidth, y, sample[x][y]);
		    }
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return model.getMap();
    }

    @Override
    public String getGeneratorName() {
	return "SamplerLevelGenerator";
    }
}
