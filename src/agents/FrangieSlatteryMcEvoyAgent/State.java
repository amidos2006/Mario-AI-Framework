package agents.FrangieSlatteryMcEvoyAgent;

import java.util.Random;

import engine.helper.MarioActions;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

public abstract class State {
    protected Agent agent;
    protected Random rnd;

    public State(Agent a) {
        agent = a;
        rnd = new Random();
    }

    protected static boolean[] createAction(boolean left, boolean right, boolean down, boolean speed, boolean jump) {
        boolean[] action = new boolean[5];
        action[MarioActions.DOWN.getValue()] = down;
        action[MarioActions.JUMP.getValue()] = jump;
        action[MarioActions.LEFT.getValue()] = left;
        action[MarioActions.RIGHT.getValue()] = right;
        action[MarioActions.SPEED.getValue()] = speed;
        return action;
    }

    // Abstract class that returns the MarioActions to be performed this frame
    public abstract boolean[] getAction(MarioForwardModel model, MarioTimer timer);

    // Abstract class that checks State transitions and returns the State to transition into
    public abstract State checkStateTransition(MarioForwardModel model, MarioTimer timer);
}
