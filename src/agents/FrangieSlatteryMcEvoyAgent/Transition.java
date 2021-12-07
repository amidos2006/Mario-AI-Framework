package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class Transition {
    State targetState;
    SFunction transitionFunction;

    Transition(State ts, SFunction tf) {
        targetState = ts;
        transitionFunction = tf;
    }
}
