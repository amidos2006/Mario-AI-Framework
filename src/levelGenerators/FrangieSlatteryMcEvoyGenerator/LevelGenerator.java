package levelGenerators.FrangieSlatteryMcEvoyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

// A custom class for generating Markov-Chain levels
public class LevelGenerator implements MarioLevelGenerator {
    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        // Load generation information
        int levelHeight = 16;

        File levelsPath = new File("./levels/original");
        ArrayList<File> files = listFiles(levelsPath);

        ArrayList<MarioLevelModelStrip> strips = new ArrayList<MarioLevelModelStrip>();

        ArrayList<ArrayList<Float>> frequencies = new ArrayList<>();

        Scanner levelScanner = null;

        for (File file : files) {
            
            // Set up a file scanner
            try {
                levelScanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // Create an array of each line of the level
            // Effectively a 2D array, with the use of String.charAt()
            String fileContents[] = new String[levelHeight];

            // Copy the file into fileContents
            for(int i = 0; i < levelHeight; i++){
                fileContents[i] = levelScanner.nextLine();
            }

            String stripText;
            int prevStrip = -1;
            // Read through every column in the file
            for(int i = 0; i < fileContents[0].length(); i++){
                stripText = "";
                for(int j = 0; j < levelHeight; j++){
                    stripText += fileContents[j].charAt(i);
                }

                // If the strip is not already in the list, add it
                int stripIndex = checkStrips(strips, stripText);
                if(stripIndex == -1){
                    strips.add(new MarioLevelModelStrip(1, levelHeight, stripText));
                    for (ArrayList<Float> arrayList : frequencies) {
                        arrayList.add(0f);
                    }
                    frequencies.add(new ArrayList<Float>(Collections.nCopies(strips.size(), 0f)));
                }

                // Increase the frequency between the previous strip and this one
                else if(prevStrip != -1){
                    float freqTemp = frequencies.get(prevStrip).get(stripIndex);
                    frequencies.get(prevStrip).set(stripIndex, ++freqTemp);
                }

                prevStrip = stripIndex;
            }
        }

        // Convert the tally of instances in the frequencies arrayList to actual frequencies
        for (ArrayList<Float> nodeList : frequencies) {
            float total = 0;
            // Search through the first time for the total number of nodes following this node
            for (float instances : nodeList) {
                total += instances;
            }

            for (int i = 0; i < nodeList.size(); i++) {
                nodeList.set(i, nodeList.get(i) / total);
            }
        }


        int nodeCount = frequencies.size();

        // Load Markov Chain nodes with data, link them with MarkovChainPaths frequency information
        ArrayList<MarkovChainNode<MarioLevelModelStrip>> nodes 
            = new ArrayList<MarkovChainNode<MarioLevelModelStrip>>(nodeCount);

        for (int i = 0; i < nodeCount; i++) {
            MarkovChainNode<MarioLevelModelStrip> node = new MarkovChainNode<MarioLevelModelStrip>(strips.get(i));
            nodes.set(i, node);

            for (int j = 0; j <= i; j++) {
                MarkovChainNode<MarioLevelModelStrip> pastNode = nodes.get(j);

                // From node[i] to node[j]
                if (frequencies.get(i).get(j) > 0) {
                    MarkovChainPath<MarioLevelModelStrip> path = new MarkovChainPath<MarioLevelModelStrip>();
                    path.node = pastNode;
                    path.frequency = frequencies.get(i).get(j);
                    node.paths.add(path);
                }

                // From node[j] To node[i]
                if (frequencies.get(j).get(i) > 0) {
                    MarkovChainPath<MarioLevelModelStrip> path = new MarkovChainPath<MarioLevelModelStrip>();
                    path.node = node;
                    path.frequency = frequencies.get(j).get(i);
                    pastNode.paths.add(path);
                }
            }
        }

        
        // Generate map using Markov Chain pattern
        MarkovChainNode<MarioLevelModelStrip> currentNode = nodes.get(0);
        
        model.clearMap();

        for (int x = 0; x < model.getWidth(); x++) {
            // Use the Mario Level Strip to add to the level
            // TODO: Ben !!!

            // Generate the next Node
            currentNode = currentNode.getNextNode();
            System.out.println(currentNode.value.strip[0]);
        }
        
        return model.getMap();
    }

    private ArrayList<File> listFiles(File startingDir){
        ArrayList<File> files = new ArrayList<File>();
        File thisDir[] = startingDir.listFiles();

        if (files != null && thisDir.length > 0) {
            for (int i = 0; i < thisDir.length; i++) {
                File file = thisDir[i];

                // Check if the file is a subfolder
                if (file.isDirectory()) {
                    listFiles(new File(file.getAbsolutePath()));

                } else {
                    files.add(file);
                }
            }
        }

        return files;
    }

    /**
     * @param strips ArrayList of all discovered Mario level strips
     * @param toFind String representing a single strip, to be found in the list of strips
     * @return the index of the strip in the list. -1 if this string has not been entered yet.
     */
    private int checkStrips(ArrayList<MarioLevelModelStrip> strips, String toFind){
        for (int i = 0; i < strips.size(); i++) {
            if(new String(strips.get(i).strip[0]).equals(toFind)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String getGeneratorName() {
        return "FrangieSlatteryMcEvoyLevelGenerator";
    }

    
}
