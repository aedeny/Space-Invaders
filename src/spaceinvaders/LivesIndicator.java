package spaceinvaders;

import java.awt.Color;

import biuoop.DrawSurface;
import main.Counter;
import main.Sprite;

/**
 * This class is in charge of indicating the remaining lives on the screen.
 * @author Eden Yefet
 */
public class LivesIndicator implements Sprite {
    private Counter numOfLives;

    /**
     * Constructor.
     * @param livesCounter The "lives" counter
     */
    LivesIndicator(Counter livesCounter) {
        this.numOfLives = livesCounter;
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
        String text = "Lives: " + this.numOfLives.getValue();
        d.setColor(Color.BLACK);
        d.drawText(10, 24, text, 25);
    }

    @Override
    public void timePassed(double dt) {
    }
}
