package agents.FrangieSlatteryMcEvoyAgent;

import engine.helper.MarioActions;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public class StateWalk extends State {
    StateWalk(Agent a) {
        super(a);
    }

    @Override 
    public boolean[] getAction(MarioForwardModel model, MarioTimer timer) {
        boolean[] action = agent.tree.optimise(model, timer);
        action[MarioActions.SPEED.getValue()] = false;
        agent.action = action;
        return action;
    }
}
