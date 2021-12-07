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

    State(Transition[] ts, SFunction ent, SFunction ex) {
        transitions = ts;
        onEnter = ent;
        onExit = ex;
    }
    
    public boolean[] getAction() {return action.getAction();}
}
