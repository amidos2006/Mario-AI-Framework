package agents.FrangieSlatteryMcEvoyAgent;

public class StateIdle extends State {
    StateIdle(Agent a) {
        super(a);
    }

    @Override public boolean[] getAction() {
        return createAction(false, false, false, false, false);
    }
}
