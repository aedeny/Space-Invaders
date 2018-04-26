package animation;

import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import geometricprimitives.Point;
import geometricprimitives.Rectangle;
import main.Sprite;
import spaceinvaders.Block;
import spaceinvaders.GameLevel;

/**
 * A background for the end screen.
 * @author Eden Yefet
 */
public class EndScreenBackground implements Sprite {
    private Block backgroundColor;
    private long startTime;

    /**
     * Constructor.
     */
    public EndScreenBackground() {
        this.backgroundColor =
                new Block(new Rectangle(new Point(0, 0), GameLevel.WINDOW_WIDTH, GameLevel.WINDOW_HEIGHT), Color.BLACK);
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void drawOn(DrawSurface d) {
        long randomDifference = 800 + new Random().nextInt(500);
        if (System.currentTimeMillis() - this.startTime > randomDifference) {
            this.startTime = System.currentTimeMillis();
        }
        this.backgroundColor.drawOn(d);
    }

    @Override
    public void timePassed(double dt) {
    }
}
