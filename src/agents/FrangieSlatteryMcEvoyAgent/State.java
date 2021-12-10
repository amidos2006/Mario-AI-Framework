package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class State {
    Transition[] transitions;
    SFunction onEnter;
    SFunction onExit;
    GetAction action;
    Agent agent;

    State(Agent a, Transition[] ts, SFunction ent, SFunction ex, GetAction ga) {
        agent = a;
        transitions = ts;
        onEnter = ent;
        onExit = ex;
        action = ga;
    }

    public void transition(int target) {
        target %= transitions.length;
        transitions[target].transitionFunction.execute();
        agent.transition(target);
    }
    
    public boolean[] getAction() {return action.getAction();}
}
