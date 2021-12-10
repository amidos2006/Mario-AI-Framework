package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class StateIdle extends State {

    StateIdle(Agent a, Transition[] ts, SFunction ent, SFunction ex) {
        super(a, ts, ent, ex);
    }

    @Override public boolean[] getAction() {return createAction(false, false, false, false, false);}
}
