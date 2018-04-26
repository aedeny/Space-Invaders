package spaceinvaders;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biuoop.DrawSurface;
import geometricprimitives.Rectangle;
import main.Collidable;
import main.HitListener;
import main.HitNotifier;
import main.Sprite;

/**
 * This class is responsible for Blocks.
 *
 * @author Eden Yefet
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rect;
    private int hitPoints;
    private Map<Integer, Color> hitsToColorMap;
    private Map<Integer, Image> hitsToImageMap;
    private Color strokeColor;

    /**
     * Block Constructor.
     *
     * @param rect  Rectangle
     * @param color The color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.hitsToColorMap = new HashMap<>();
        this.hitsToImageMap = new HashMap<>();
        this.rect = rect;
        this.hitPoints = -1;
        this.hitsToColorMap.put(-1, color);
        this.hitListeners = new ArrayList<>();
        this.strokeColor = Color.BLACK;
    }

    /**
     * Block Constructor.
     *
     * @param rect            Rectangle
     * @param initialHitCount The initial hit count.
     * @param color           The color of the block.
     */
    public Block(Rectangle rect, int initialHitCount, Color color) {
        this.hitsToColorMap = new HashMap<>();
        this.hitsToImageMap = new HashMap<>();
        this.rect = rect;
        this.hitPoints = initialHitCount;
        this.hitsToColorMap.put(-1, color);
        this.hitListeners = new ArrayList<>();
        this.strokeColor = null;
    }

    @Override public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Adds the block to the game.
     *
     * @param g The game.
     */
    void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Decrease the hits counter of the Block in case it's greater than 0.
     */
    private void decreaseHitsCounter() {
        if (this.hitPoints > 0) {
            this.hitPoints -= 1;
        }
    }

    /**
     * Draw fill.
     *
     * @param d the d
     */
    private void drawFill(DrawSurface d) {
        Color color;
        Image image;

        if (this.hitsToColorMap.containsKey(this.hitPoints)) {
            color = this.hitsToColorMap.get(this.hitPoints);
            this.fillRectWithColor(d, color);

        } else if (this.hitsToImageMap.containsKey(this.hitPoints)) {
            image = this.hitsToImageMap.get(this.hitPoints);
            d.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(), image);

        } else if (this.hitsToColorMap.containsKey(-1)) {
            color = this.hitsToColorMap.get(-1);
            this.fillRectWithColor(d, color);

        } else if (this.hitsToImageMap.containsKey(-1)) {
            image = this.hitsToImageMap.get(-1);
            d.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(), image);
        }
    }

    @Override public void drawOn(DrawSurface d) {
        this.drawFill(d);

        // Draws white frames in case the block is a hit brick.
        if (this.strokeColor != null) {
            d.setColor(this.strokeColor);
            d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
    }

    /**
     * Fill rect with color.
     *
     * @param d     the d
     * @param color the color
     */
    private void fillRectWithColor(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * @return The collision rectangle of the block.
     */
    @Override public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Gets the hit points.
     *
     * @return The hit points of the blocks.
     */
    int getHitPoints() {
        return this.hitPoints;
    }

    @Override public void hit(Ball hitter) {
        this.decreaseHitsCounter();
        this.notifyHit(hitter);
    }

    /**
     * Notifies the listeners that this block has been hit.
     *
     * @param hitter The Ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Removes the block from the game.
     *
     * @param g GameLevel
     */
    void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override public void timePassed(double dt) {

    }

}
