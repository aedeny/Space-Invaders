package spaceinvaders;

import java.awt.Color;

import biuoop.DrawSurface;
import main.Sprite;

/**
 * This class is in charge of indicating the level name on the screen.
 * @author Eden Yefet
 */
public class LevelNameIndicator implements Sprite {
    private String lvlName;

    /**
     * Constructor.
     * @param levelName The name of the level
     */
    LevelNameIndicator(String levelName) {
        this.lvlName = levelName;
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
        String text = "Level: " + this.lvlName;
        d.setColor(Color.BLACK);
        d.drawText(530, 21, text, 20);

    }

    @Override
    public void timePassed(double dt) {
    }

}
