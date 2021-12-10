package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;
import java.util.Random;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

import engine.helper.GameStatus;

public class Agent implements MarioAgent {
    private Random rnd;
    private ArrayList<boolean[]> choices;
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
        rnd = new Random();
        choices = new ArrayList<>();
        //right run
        choices.add(new boolean[]{false, true, false, true, false});
        //right jump and run
        choices.add(new boolean[]{false, true, false, true, true});
        // right
        choices.add(new boolean[]{false, true, false, false, false});
        // right jump
        choices.add(new boolean[]{false, true, false, false, true});
        //left
        choices.add(new boolean[]{true, false, false, false, false});
        //left run
        choices.add(new boolean[]{true, false, false, true, false});
        //left jump
        choices.add(new boolean[]{true, false, false, false, true});
        //left jump and run
        choices.add(new boolean[]{true, false, false, true, true});

        greed = rnd.nextFloat(); //collect coins
        fightorflight = rnd.nextFloat(); //high to kill enemies, low to run away
        frog = rnd.nextFloat(); //jumps
        smash = rnd.nextFloat(); //breaks blocks
        boost = rnd.nextFloat(); //collects powerups
        sadness = rnd.nextFloat(); //dies
        zoomer = rnd.nextFloat(); //gotta go fast
        states = new State[8];
        states[0] = new StateIdle(this, null, null, null);
        states[1] = new StateIdle2(this, null, null, null);
        currentState = 1;
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        return states[currentState].getAction();
    }

    @Override
    public String getAgentName() {
        return "FrangieSlatteryMcEvoyAgent";
    }

    public void transition(int i) {
        currentState = i;
        if (states[currentState].onEnter != null) {
            states[currentState].onEnter.execute();
        }
    }

    int percentChance(float[] list) {
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
