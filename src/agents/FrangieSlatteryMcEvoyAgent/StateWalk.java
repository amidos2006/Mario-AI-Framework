package agents.FrangieSlatteryMcEvoyAgent;

import engine.helper.MarioActions;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public class StateWalk extends State {
    static int MAX_FRAMES = 100, MIN_FRAMES = 10;
    private int walkFrames;

    private boolean[] action;
    private AStarTree tree;
    
    StateWalk(Agent a) {
        super(a);
        this.action = new boolean[MarioActions.numberOfActions()];
        this.tree = new AStarTree();
        walkFrames = (int) (rnd.nextFloat() * (MAX_FRAMES - MIN_FRAMES)) + MIN_FRAMES;
    }

    @Override 
    public boolean[] getAction(MarioForwardModel model, MarioTimer timer) {
        action = this.tree.optimise(model, timer);
        action[MarioActions.SPEED.getValue()] = false;
        return action;
    }


    @Override
    public State checkStateTransition(MarioForwardModel model, MarioTimer timer) {
        walkFrames--;
        if (walkFrames == 0) {
            return new StateRun(agent);
        }

        if (tree.overwhelmed()) {
            return new StateRandom(agent);
        }
        
        return this;
    }
}
