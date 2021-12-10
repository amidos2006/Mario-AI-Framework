package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;

import engine.core.MarioForwardModel;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class GetIdleAction extends GetAction {
    @Override public boolean[] getAction() {return createAction(false, false, false, false, false);}
}
