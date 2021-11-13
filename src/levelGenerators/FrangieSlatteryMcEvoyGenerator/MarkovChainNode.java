package levelGenerators.FrangieSlatteryMcEvoyGenerator;

import java.util.ArrayList;
import java.util.Random;

public class MarkovChainNode<T> {
    public T value;
    public ArrayList<MarkovChainPath<T>> paths;

    public MarkovChainNode<T> getNextNode() {
        MarkovChainNode<T> nextNode = null;
        
        Random random = new Random();
        float targValue = random.nextFloat();

        for (MarkovChainPath<T> path : paths) {
            nextNode = path.node;
            targValue -= path.frequency;
            if (targValue <= 0) break;
        }

        return nextNode;
    }
}