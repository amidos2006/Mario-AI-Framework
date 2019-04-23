package engine.core;

public interface MarioAgent {
    void initialize(MarioForwardModel model, MarioTimer timer);
    boolean[] getActions(MarioForwardModel model, MarioTimer timer);
    String getAgentName();
}
