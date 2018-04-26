package spaceinvaders;

import java.awt.Color;

import animation.Animation;
import biuoop.DrawSurface;

/**
 * Pause screen class.
 * @author Eden Yefet
 */
public class PauseScreen implements Animation {
    private SpriteCollection gameScreen;
    private boolean stop;
    private Color textColor;

    /**
     * Constructor.
     * @param gameScreen The SpriteCollection of the current game.
     * @param textColor The color of the text.
     */
    PauseScreen(SpriteCollection gameScreen, Color textColor) {
        this.gameScreen = gameScreen;
        this.stop = false;
        this.textColor = textColor;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(this.textColor);
        d.drawText(d.getWidth() / 2 - 70, d.getHeight() / 2, "PAUSED", 35);
        d.drawText(d.getWidth() / 2 - 105, d.getHeight() / 2 + 40, "press SPACE to continue", 20);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
