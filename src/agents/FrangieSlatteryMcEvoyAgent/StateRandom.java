package agents.FrangieSlatteryMcEvoyAgent;

public class StateRandom extends State {
    StateRandom(Agent a) {
        super(a);
    }

    @Override public boolean[] getAction() {
        return createAction(
            rnd.nextBoolean(),
            rnd.nextBoolean(),
            rnd.nextBoolean(),
            rnd.nextBoolean(),
            rnd.nextBoolean()
        );
    }
}
