package agents.FrangieSlatteryMcEvoyAgent;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public class Agent implements MarioAgent {
    private State state;


    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        state = new StateIdle(this);
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        System.out.println(state);
        state = state.checkStateTransition(model, timer);
        return state.getAction(model, timer);
    }

    @Override
    public String getAgentName() {
        return "FrangieSlatteryMcEvoyAgent";
    }
}
