package agents.FrangieSlatteryMcEvoyAgent;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class StateRun extends State {
    static int MAX_FRAMES = 60, MIN_FRAMES = 15;
    private int runFrames;

    private boolean[] action;
    private AStarTree tree;

    StateRun(Agent a) {
        super(a);
        this.action = new boolean[MarioActions.numberOfActions()];
        this.tree = new AStarTree();
        runFrames = (int) (rnd.nextFloat() * (MAX_FRAMES - MIN_FRAMES)) + MIN_FRAMES;
    }

    @Override 
    public boolean[] getAction(MarioForwardModel model, MarioTimer timer) {
        action = this.tree.optimise(model, timer);
        return action;
    }

    @Override
    public State checkStateTransition(MarioForwardModel model, MarioTimer timer) {
        runFrames--;
        if (runFrames == 0) {
            return new StateWalk(agent);
        }

        if (tree.overwhelmed()) {
            return new StateRandom(agent);
        }
        
        return this;
    }
}
