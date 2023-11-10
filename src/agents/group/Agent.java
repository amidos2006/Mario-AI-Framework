package agents.group;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {

    private enum JumpType {
        ENEMY, GAP, WALL, NONE
    }

    private class Rectangle {
        private float x, y, width, height;

        public Rectangle(float x, float y, float width, float height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean contains(float x, float y) {
            return x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.height;
        }
    }

    private agents.group.Agent.JumpType jumpType = agents.group.Agent.JumpType.NONE;
    private int jumpCount = 0, jumpSize = -1;
    private float prevY = 0;
    private boolean[] action;

    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        action = new boolean[MarioActions.numberOfActions()];
        action[MarioActions.RIGHT.getValue()] = true;
        action[MarioActions.SPEED.getValue()] = true;
    }

    private int getWallHeight(int tileX, int tileY, int[][] levelScene) {
        int y = tileY + 1, wallHeight = 0;
        while (y-- > 0 && levelScene[tileX + 1][y] != 0) {
            wallHeight++;
        }
        return wallHeight;
    }

    private boolean dangerOfGap(int tileX, int tileY, int[][] levelScene) {
        for (int y = tileY + 1; y < levelScene[0].length; y++) {
            if (levelScene[tileX + 1][y] != 0) {
                return false;
            }
        }
        return true;
    }
    private boolean enemyInRange(MarioForwardModel e, agents.group.Agent.Rectangle r) {
        for (int i = 0; i < e.getEnemiesFloatPos().length; i += 3) {
            if (r.contains(e.getEnemiesFloatPos()[i + 1] - e.getMarioFloatPos()[0],
                    e.getMarioFloatPos()[1] - e.getEnemiesFloatPos()[i + 2])) {
                return true;
            }
        }
        return false;
    }

    private final void setJump(final agents.group.Agent.JumpType type, final int size) {
        jumpType = type;
        jumpSize = size;
        jumpCount = 0;
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        final float marioSpeed = model.getMarioFloatVelocity()[0];
        final boolean dangerOfEnemy = enemyInRange(model, new agents.group.Agent.Rectangle(-13, -57, 70, 87));
        final boolean dangerOfEnemyAbove = enemyInRange(model, new agents.group.Agent.Rectangle(-28, 28, 70, 45));
        final boolean dangerOfGap = dangerOfGap(model.getMarioScreenTilePos()[0], model.getMarioScreenTilePos()[1],
                model.getScreenSceneObservation());
        if ((model.isMarioOnGround() || model.mayMarioJump()) && !jumpType.equals(agents.group.Agent.JumpType.NONE)) {
            setJump(agents.group.Agent.JumpType.NONE, -1);
        } else if (model.mayMarioJump()) {
            final int wallHeight = getWallHeight(model.getMarioScreenTilePos()[0], model.getMarioScreenTilePos()[1],
                    model.getScreenSceneObservation());
            if (dangerOfGap && marioSpeed > 0) {
                setJump(agents.group.Agent.JumpType.GAP, marioSpeed < 6 ? (int) (9 - marioSpeed) : 1);
            } else if (marioSpeed <= 1 && !dangerOfEnemyAbove && wallHeight > 0) {
                setJump(agents.group.Agent.JumpType.WALL, wallHeight >= 4 ? wallHeight + 3 : wallHeight);
            } else if (dangerOfEnemy && !(dangerOfEnemyAbove && marioSpeed > 2)) {
                setJump(agents.group.Agent.JumpType.ENEMY, 7);
            }
        } else {
            jumpCount++;
        }
        final boolean isFalling = prevY < model.getMarioFloatPos()[1] && jumpType.equals(agents.group.Agent.JumpType.NONE);
        action[MarioActions.LEFT.getValue()] = isFalling && ((dangerOfEnemy && dangerOfEnemyAbove) || dangerOfGap);
        action[MarioActions.RIGHT.getValue()] = !isFalling && !(dangerOfEnemyAbove && jumpType == agents.group.Agent.JumpType.WALL);
        action[MarioActions.JUMP.getValue()] = !jumpType.equals(agents.group.Agent.JumpType.NONE) && jumpCount < jumpSize;
        action[MarioActions.SPEED.getValue()] = !(jumpType.equals(agents.group.Agent.JumpType.ENEMY) && action[MarioActions.SPEED.getValue()] && model.getMarioMode() == 2);
        prevY = model.getMarioFloatPos()[1];
        return action;
    }


    @Override
    public String getAgentName() {
        return "DoNothingAgent";
    }
}
