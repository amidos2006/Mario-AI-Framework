package agents.FrangieSlatteryMcEvoyAgent;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public class StateIdle extends State {
    StateIdle(Agent a) {
        super(a);
    }

    @Override 
    public boolean[] getAction(MarioForwardModel model, MarioTimer timer) {
        return createAction(false, false, false, false, false);
    }
}
