import java.awt.*;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import engine.core.MarioGame;
import engine.core.MarioRender;
import engine.core.MarioResult;
import engine.core.MarioWorld;

import javax.swing.*;

public class Replay {
    private final MarioRender render;
    private final VolatileImage renderTarget;
    private final Graphics backBuffer;
    private final Graphics currentBuffer;

    public static String getLevel(String filepath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void update(MarioWorld world) {
        this.render.renderWorld(world, renderTarget, backBuffer, currentBuffer);
    }

    Replay() {
        JFrame window = new JFrame("Replay");
        this.render = new MarioRender(2);
        window.setContentPane(this.render);
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.render.init();
        window.setVisible(true);

        //initialize graphics
        renderTarget = this.render.createVolatileImage(MarioGame.width, MarioGame.height);
        backBuffer = this.render.getGraphics();
        currentBuffer = renderTarget.getGraphics();
        this.render.addFocusListener(this.render);
    }

    public static void main(String[] args) {
        Replay replay = new Replay();
        MarioGame game = new MarioGame();
        MarioResult result = game.playGame(getLevel("./levels/original/lvl-1.txt"), 200, 0);
        replay.update(result.getWorld());
        MarioGame game2 = new MarioGame();
        MarioResult result2 = game2.runGame(new agents.robinBaumgarten.Agent(), getLevel("./levels/original/lvl-1.txt"), 20, 0, true);
        replay.update(result2.getWorld());
    }
}
