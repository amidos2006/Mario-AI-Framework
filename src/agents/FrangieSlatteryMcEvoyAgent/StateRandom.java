package agents.FrangieSlatteryMcEvoyAgent;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public class StateRandom extends State {
    static int MAX_FRAMES = 3, MIN_FRAMES = 1;
    private int randomFrames;
    
    
    StateRandom(Agent a) {
        super(a);
        randomFrames = (int) (rnd.nextFloat() * (MAX_FRAMES - MIN_FRAMES)) + MIN_FRAMES;
    }

    @Override 
    public boolean[] getAction(MarioForwardModel model, MarioTimer timer) {
        return createAction(
            rnd.nextBoolean(),
            rnd.nextBoolean(),
            rnd.nextBoolean(),
            rnd.nextBoolean(),
            rnd.nextBoolean()
        );
    }

    @Override
    public State checkStateTransition(MarioForwardModel model, MarioTimer timer) {
        randomFrames--;
        if (randomFrames == 0) {
            return new StateIdle(agent);
        }
        return this;
    }
}
