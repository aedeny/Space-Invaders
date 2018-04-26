package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import main.Counter;
import main.Sprite;

/**
 * An End Screen for the game.
 * @author Eden Yefet
 */
public class EndScreen implements Animation {
    private Counter score;
    private Sprite background;
    private boolean stop;

    /**
     * Constructor.
     * @param score A counter for the score
     */
    public EndScreen(Counter score) {
        this.score = score;
        this.background = new EndScreenBackground();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        int xOffset = -50;

        this.background.drawOn(d);
        d.setColor(Color.WHITE);

        d.setColor(new Color(255, 169, 15));
        d.drawText(100 + xOffset, 100, "GAME OVER", 60);
        d.setColor(new Color(254, 235, 176));
        d.drawText(105 + xOffset, 100, "GAME OVER", 60);
        d.setColor(Color.WHITE);
        d.drawText(100 + xOffset, 200, "Your score is " + Integer.toString(this.score.getValue()), 40);
        d.drawText(100 + xOffset, 230, "press SPACE to close", 20);

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
