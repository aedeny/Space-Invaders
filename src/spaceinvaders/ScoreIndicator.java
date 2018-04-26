package spaceinvaders;

import java.awt.Color;

import biuoop.DrawSurface;
import main.Counter;
import main.Sprite;

/**
 * This class is in charge of indicating the score on the screen.
 * @author Eden Yefet
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Color background;

    /**
     * Constructor.
     * @param scoreCounter Score counter
     */
    ScoreIndicator(Counter scoreCounter) {
        this.score = scoreCounter;
        this.background = Color.lightGray;
    }

    /**
     * Adds this indicator to the game.
     * @param g GameLevel
     */
    void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.background);
        d.fillRectangle(0, 0, 800, 30);
        d.setColor(Color.BLACK);
        d.drawText(300, 25, "Score: " + Integer.toString(this.score.getValue()), 28);

    }

    @Override
    public void timePassed(double dt) {
    }

}
