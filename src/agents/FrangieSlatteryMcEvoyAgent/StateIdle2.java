package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class StateIdle2 extends State {
    StateIdle2(Agent a, Transition[] ts, SFunction ent, SFunction ex) {
        super(a, ts, ent, ex);
    }

    @Override public boolean[] getAction() {
        return createAction(rnd.nextFloat()<0.1?true:false,
            rnd.nextFloat()<0.1?true:false,
            rnd.nextFloat()<0.1?true:false,
            rnd.nextFloat()<0.1?true:false,
            rnd.nextFloat()<0.1?true:false);
    }
}
