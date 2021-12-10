package agents.FrangieSlatteryMcEvoyAgent;

import java.util.Random;

import engine.helper.MarioActions;

public abstract class State {
    protected Agent agent;
    protected Random rnd;

    public State(Agent a) {
        agent = a;
        rnd = new Random();
    }

    public abstract boolean[] getAction();

    protected static boolean[] createAction(boolean left, boolean right, boolean down, boolean speed, boolean jump) {
        boolean[] action = new boolean[5];
        action[MarioActions.DOWN.getValue()] = down;
        action[MarioActions.JUMP.getValue()] = jump;
        action[MarioActions.LEFT.getValue()] = left;
        action[MarioActions.RIGHT.getValue()] = right;
        action[MarioActions.SPEED.getValue()] = speed;
        return action;
    }
}
