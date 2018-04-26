package spaceinvaders;

import java.awt.Color;

import animation.Animation;
import biuoop.DrawSurface;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 * @author Eden Yefet
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private long startTime;
    private Color color;
    private double durationOfOneCount;
    private int countFrom;

    /**
     * Constructor.
     * @param numOfSeconds Number of seconds to count
     * @param countFrom The number to count from down to 1
     * @param gameScreen The SpriteCollection of the game
     * @param color The color of the text
     */
    CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Color color) {
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
        this.countFrom = countFrom;
        this.color = color;
        this.durationOfOneCount = numOfSeconds / countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (System.currentTimeMillis() - this.startTime > this.durationOfOneCount * 1000) {
            this.countFrom--;
            this.startTime = System.currentTimeMillis();
        }

        this.gameScreen.drawAllOn(d);
        if (this.countFrom > 0) {
            String text = Integer.toString(this.countFrom);
            d.setColor(this.color);
            d.drawText(d.getWidth() / 2 - 12, d.getHeight() / 2, text, 60);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom <= 0;
    }
}
