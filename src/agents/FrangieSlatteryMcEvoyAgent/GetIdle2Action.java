package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class GetIdle2Action extends GetAction {
    @Override public boolean[] getAction() {
        return createAction(rnd.nextFloat()<0.1?true:false,
            rnd.nextFloat()<0.1?true:false,
            rnd.nextFloat()<0.1?true:false,
            rnd.nextFloat()<0.1?true:false,
            rnd.nextFloat()<0.1?true:false);
    }
}
