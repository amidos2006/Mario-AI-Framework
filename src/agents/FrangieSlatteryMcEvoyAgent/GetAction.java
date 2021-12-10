package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;
import java.util.Random;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class GetAction {
    protected Random rnd;

    public GetAction() {
        rnd = new Random();
    }

    public boolean[] getAction() {return null;}

    //Copied from robinBaumgarten.Helper.java
    public static boolean[] createAction(boolean left, boolean right, boolean down, boolean jump, boolean speed) {
        boolean[] action = new boolean[5];
        action[MarioActions.DOWN.getValue()] = down;
        action[MarioActions.JUMP.getValue()] = jump;
        action[MarioActions.LEFT.getValue()] = left;
        action[MarioActions.RIGHT.getValue()] = right;
        action[MarioActions.SPEED.getValue()] = speed;
        return action;
    }
}
