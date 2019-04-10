package engine.core;

import java.awt.image.VolatileImage;
import java.awt.*;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;

import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class MarioGame{
    //system parameters
    public static final long maxTime = 40;
    public static final long graceTime = 10;
    public static final int width = 256;
    public static final int height = 240;
    public static final boolean verbose = false;
    
    public boolean pause = false;
    
    //visualization
    private JFrame window = null;
    private MarioRender render = null;
    private MarioAgent agent = null;
    private MarioWorld world = null;
    
    public MarioGame() {
	
    }
    
    private int getDelay(int fps) {
	if(fps <= 0) {
	    return 0;
	}
	return 1000 / fps;
    }
    
    private void setAgent(MarioAgent agent) {
	this.agent = agent;
        if (agent instanceof KeyAdapter) {
            this.render.addKeyListener((KeyAdapter) this.agent);
        }
    }
    
    public void runNoVisuals(MarioAgent agent, String level, int timer) {
	this.setAgent(agent);
	this.gameLoop(level, timer, false, 0);
    }
    
    public void runVisuals(MarioAgent agent, String level, int timer, int fps, float scale) {
	this.window = new JFrame("Mario AI Framework");
	this.render = new MarioRender(scale);
	this.window.setContentPane(this.render);
	this.window.pack();
	this.window.setResizable(false);
	this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.render.init();
	this.window.setVisible(true);
	
	this.setAgent(agent);
	this.gameLoop(level, timer, true, fps);
    }
    
    private void gameLoop(String level, int timer, boolean visual, int fps) {
	this.world = new MarioWorld();
	this.world.visuals = visual;
	this.world.initializeLevel(level, 1000 * timer);
	if(visual) {
	    this.world.initializeVisuals(this.render.getGraphicsConfiguration());
	}
	this.world.update(new boolean[MarioActions.numberOfActions()]);
	long currentTime = System.currentTimeMillis();
	
	//initialize graphics
	VolatileImage renderTarget = null;
	Graphics backBuffer = null;
	Graphics currentBuffer = null;
	if(visual) {
	    renderTarget = this.render.createVolatileImage(MarioGame.width, MarioGame.height);
	    backBuffer = this.render.getGraphics();
	    currentBuffer = renderTarget.getGraphics();
	    this.render.addFocusListener(this.render);
	}
	
	MarioTimer agentTimer = new MarioTimer(MarioGame.maxTime);
	this.agent.initialize(new MarioForwardModel(this.world.clone()), agentTimer);
	
	while(this.world.gameStatus == GameStatus.RUNNING) {
	    if(!this.pause) {
		//get actions
		agentTimer = new MarioTimer(MarioGame.maxTime);
		boolean[] actions = this.agent.getActions(new MarioForwardModel(this.world.clone()), agentTimer);
		if (MarioGame.verbose) {
		    if (agentTimer.getRemainingTime() < 0 && Math.abs(agentTimer.getRemainingTime()) > MarioGame.graceTime) {
			System.out.println("The Agent is slowing down the game by: "
				+ Math.abs(agentTimer.getRemainingTime()) + " msec.");
		    }
		}
		// update world
		this.world.update(actions);
	    }
	    
	    //render world
	    if(visual) {
		this.render.renderWorld(this.world, renderTarget, backBuffer, currentBuffer);
	    }
	    //check if delay needed
	    if (this.getDelay(fps) > 0) {
              try {
                  currentTime += this.getDelay(fps);
                  Thread.sleep(Math.max(0, currentTime - System.currentTimeMillis()));
              } catch (InterruptedException e) {
                  break;
              }
	    }
	}
	System.out.println(world.maxXJump + " " + world.numJumps + " " + world.jumpAirTime);
    }
}
