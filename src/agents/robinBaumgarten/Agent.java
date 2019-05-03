package agents.robinBaumgarten;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

/**
 * @author RobinBaumgarten
 */
public class Agent implements MarioAgent{
    private boolean action[];
    private AStarTree tree;
    
    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
	this.action = new boolean[MarioActions.numberOfActions()];
	this.tree = new AStarTree();
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
	System.out.println("------------------------------");
	System.out.println("\tMario Original: " + model.getGameStatus() + ", " + model.getMarioFloatPos()[0] + ", " + model.getMarioFloatPos()[1] + ", " + model.getMarioFloatVelocity()[0]);
	action = this.tree.optimise(model, timer);
	return action;
    }

    @Override
    public String getAgentName() {
	return "RobinBaumgartenAgent";
    }

}
