package engine.core;

import java.awt.image.VolatileImage;
import java.awt.*;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;

import agents.human.Agent;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class MarioGame{
    //system parameters
    public static final long maxTime = 40;
    public static final long graceTime = 10;
    public static final int width = 256;
    public static final int height = 256;
    public static final int tileWidth = width/16;
    public static final int tileHeight = height/16;
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
    
    public MarioResult playGame(String level, int timer) {
	return this.runGame(new Agent(), level, timer, 0, true, 30, 2);
    }
    
    public MarioResult playGame(String level, int timer, int marioState) {
	return this.runGame(new Agent(), level, timer, marioState, true, 30, 2);
    }
    
    public MarioResult playGame(String level, int timer, int marioState, int fps) {
	return this.runGame(new Agent(), level, timer, marioState, true, fps, 2);
    }
    
    public MarioResult playGame(String level, int timer, int marioState, int fps, float scale) {
	return this.runGame(new Agent(), level, timer, marioState, true, fps, scale);
    }
    
    public MarioResult runGame(MarioAgent agent, String level, int timer) {
	return this.runGame(agent, level, timer, 0, false, 0, 2);
    }
    
    public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState) {
	return this.runGame(agent, level, timer, marioState, false, 0, 2);
    }
    
    public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals) {
	return this.runGame(agent, level, timer, marioState, visuals, visuals?30:0, 2);
    }
    public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals, int fps) {
	return this.runGame(agent, level, timer, marioState, visuals, fps, 2);
    }
    
    public MarioResult runGame(MarioAgent agent, String level, int timer, int marioState, boolean visuals, int fps, float scale) {
	if (visuals) {
	    this.window = new JFrame("Mario AI Framework");
	    this.render = new MarioRender(scale);
	    this.window.setContentPane(this.render);
	    this.window.pack();
	    this.window.setResizable(false);
	    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.render.init();
	    this.window.setVisible(true);
	}
	this.setAgent(agent);
	return this.gameLoop(level, timer, marioState, visuals, fps);
    }
    
    private MarioResult gameLoop(String level, int timer, int marioState, boolean visual, int fps) {
	this.world = new MarioWorld();
	this.world.timeScale = fps > 0 ? fps/30f : 1;
	this.world.visuals = visual;
	this.world.initializeLevel(level, 1000 * timer);
	if(visual) {
	    this.world.initializeVisuals(this.render.getGraphicsConfiguration());
	}
	this.world.mario.isLarge = marioState > 0;
	this.world.mario.isFire = marioState > 1;
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
	return new MarioResult(this.world);
    }
}
