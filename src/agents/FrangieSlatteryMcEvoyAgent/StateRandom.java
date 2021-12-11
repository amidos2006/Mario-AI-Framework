package agents.FrangieSlatteryMcEvoyAgent;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public class StateRandom extends State {
    StateRandom(Agent a) {
        super(a);
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
}
