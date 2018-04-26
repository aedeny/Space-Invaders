package spaceinvaders;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import main.Sprite;

/**
 * This class is responsible for all the Sprite objects.
 * @author Eden Yefet
 */
public class SpriteCollection {
    private List<Sprite> spritesList = new ArrayList<Sprite>();

    /**
     * Adds the sprite.
     * @param s A sprite object to be added.
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * Call drawOn(d) on all sprites.
     * @param d DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spritesList);
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Calls timePassed() on all sprites.
     * @param dt the dt
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spritesList);
        for (Sprite s : sprites) {
            s.timePassed(dt);
        }
    }

    /**
     * Removes the sprite.
     * @param s A sprite object to be removed.
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);
    }

}
