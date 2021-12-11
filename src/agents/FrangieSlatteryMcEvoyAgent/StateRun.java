package agents.FrangieSlatteryMcEvoyAgent;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public class StateRun extends State {
    StateRun(Agent a) {
        super(a);
    }

    @Override 
    public boolean[] getAction(MarioForwardModel model, MarioTimer timer) {
        boolean[] action = agent.tree.optimise(model, timer);
        agent.action = action;
        return action;
    }
}
