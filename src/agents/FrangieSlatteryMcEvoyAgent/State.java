package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;
import java.util.Random;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class State {
    Transition[] transitions;
    public SFunction onEnter;
    SFunction onExit;
    Agent agent;
    
    protected Random rnd;

    State(Agent a, Transition[] ts, SFunction ent, SFunction ex) {
        agent = a;
        transitions = ts;
        onEnter = ent;
        onExit = ex;
        rnd = new Random();
    }

    public void transition(int target) {
        target %= transitions.length;
        if (onExit != null) onExit.execute();
        if (transitions[target].transitionFunction != null) transitions[target].transitionFunction.execute();
        agent.transition(target);
    }

    public boolean[] getAction() {return null;}

    //Copied from robinBaumgarten.Helper.java
    public static boolean[] createAction(boolean left, boolean right, boolean down, boolean speed, boolean jump) {
        boolean[] action = new boolean[5];
        action[MarioActions.DOWN.getValue()] = down;
        action[MarioActions.JUMP.getValue()] = jump;
        action[MarioActions.LEFT.getValue()] = left;
        action[MarioActions.RIGHT.getValue()] = right;
        action[MarioActions.SPEED.getValue()] = speed;
        return action;
    }
}
