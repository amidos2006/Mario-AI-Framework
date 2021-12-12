package agents.FrangieSlatteryMcEvoyAgent;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public class StateIdle extends State {
    static int MAX_FRAMES = 20, MIN_FRAMES = 5;
    private int idleFrames;
    
    StateIdle(Agent a) {
        super(a);
        idleFrames = (int) (rnd.nextFloat() * (MAX_FRAMES - MIN_FRAMES)) + MIN_FRAMES;
    }

    @Override 
    public boolean[] getAction(MarioForwardModel model, MarioTimer timer) {
        return createAction(false, false, false, false, false);
    }

    @Override
    public State checkStateTransition(MarioForwardModel model, MarioTimer timer) {
        idleFrames--;
        if (idleFrames == 0) {
            return new StateWalk(agent);
        }
        return this;
    }
}
