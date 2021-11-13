package levelGenerators.FrangieSlatteryMcEvoyGenerator;

import java.util.ArrayList;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

// A custom class for generating Markov-Chain levels
public class LevelGenerator implements MarioLevelGenerator {
    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        // Load generation information
        ArrayList<MarioLevelModelStrip> strips = null;
        int nodeCount = 0;
        float[][] frequencies = null;

        // TODO: Jack - How to generate the data and frequncies !!! 


        // Load Markov Chain nodes with data, link them with MarkovChainPaths frequency information
        ArrayList<MarkovChainNode<MarioLevelModelStrip>> nodes 
            = new ArrayList<MarkovChainNode<MarioLevelModelStrip>>(nodeCount);

        for (int i = 0; i < nodeCount; i++) {
            MarkovChainNode<MarioLevelModelStrip> node = new MarkovChainNode<MarioLevelModelStrip>(strips.get(i));
            nodes.set(i, node);

            for (int j = 0; j <= i; j++) {
                MarkovChainNode<MarioLevelModelStrip> pastNode = nodes.get(j);

                // From node[i] to node[j]
                if (frequencies[i][j] > 0) {
                    MarkovChainPath<MarioLevelModelStrip> path = new MarkovChainPath<MarioLevelModelStrip>();
                    path.node = pastNode;
                    path.frequency = frequencies[i][j];
                    node.paths.add(path);
                }

                // From node[j] To node[i]
                if (frequencies[j][i] > 0) {
                    MarkovChainPath<MarioLevelModelStrip> path = new MarkovChainPath<MarioLevelModelStrip>();
                    path.node = node;
                    path.frequency = frequencies[j][i];
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
        }
        
        return model.getMap();
    }

    @Override
    public String getGeneratorName() {
        return "FrangieSlatteryMcEvoyLevelGenerator";
    }

    
}
