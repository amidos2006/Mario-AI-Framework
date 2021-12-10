package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class Transition {
    int targetState;
    SFunction transitionFunction;

    Transition(int ts, SFunction tf) {
        targetState = ts;
        transitionFunction = tf;
    }
}
