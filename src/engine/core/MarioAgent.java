package engine.core;

public interface MarioAgent {
    void initialize(MarioForwardModel world, MarioTimer timer);
    boolean[] getActions(MarioForwardModel world, MarioTimer timer);
    String getAgentName();
}
