package agents.FrangieSlatteryMcEvoyAgent;

import java.util.Random;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
    public boolean[] action;
    public AStarTree tree;


    private Random rnd;
    private int currentState;
    private State[] states;
        //0 - Idle
        //1 - Random Idle (Idle2)
        //2 - Walk
        //3 - Run
        //4 - Kill
        //5 - Avoid
        //6 - Collect
        //7 - Break

    float greed; //collect coins
    float fightorflight; //high to kill enemies, low to run away
    float frog; //jumps
    float smash; //breaks blocks
    float boost; //collects powerups
    float sadness; //dies
    float zoomer; //gotta go fast


    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        this.action = new boolean[MarioActions.numberOfActions()];
        this.tree = new AStarTree();

        rnd = new Random();

        // greed = rnd.nextFloat(); //collect coins
        // fightorflight = rnd.nextFloat(); //high to kill enemies, low to run away
        // frog = rnd.nextFloat(); //jumps
        // smash = rnd.nextFloat(); //breaks blocks
        // boost = rnd.nextFloat(); //collects powerups
        // sadness = rnd.nextFloat(); //dies
        // zoomer = rnd.nextFloat(); //gotta go fast
        
        states = new State[8];
        states[0] = new StateIdle(this);
        states[1] = new StateRandom(this);
        states[2] = new StateRun(this);
        states[3] = new StateWalk(this);
        currentState = 1;
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        return states[currentState].getAction(model, timer);
    }

    @Override
    public String getAgentName() {
        return "FrangieSlatteryMcEvoyAgent";
    }

    protected int percentChance(float[] list) {
        float total = 0f;
        for (int i = 0; i < list.length; i++) {
            total += list[i];
        }
        float random = rnd.nextFloat();
        for (int i = 0; i < list.length; i++) {
            if (random <= list[i]/total) return i;
            else random -= list[i]/total;
        }
        return -1;
    }
}
