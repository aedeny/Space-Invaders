package io;

import java.awt.Color;

import biuoop.DrawSurface;
import main.Sprite;

/**
 * The Class ColorBackground.
 * @author Eden Yefet
 */
public class ColorBackground implements Sprite {
    private Color color;

    /**
     * Instantiates a new color background.
     * @param color the color
     */
    public ColorBackground(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    @Override
    public void timePassed(double dt) {
    }

}
