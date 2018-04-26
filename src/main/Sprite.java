package main;

import biuoop.DrawSurface;

/**
 * The Sprite interface.
 * @author Eden Yefet
 */
public interface Sprite {

    /**
     * Draws the sprite on the screen.
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * @param dt the dt
     */
    void timePassed(double dt);
}
