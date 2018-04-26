package io;

import java.awt.Image;

import biuoop.DrawSurface;
import main.Sprite;

/**
 * The Class ImageBackground.
 * @author Eden Yefet
 */
public class ImageBackground implements Sprite {
    private Image img;
    private int xCord;
    private int yCord;

    /**
     * Instantiates a new image background.
     * @param img the img
     */
    public ImageBackground(Image img) {
        this.img = img;
        this.xCord = 0;
        this.yCord = 0;
    }

    /**
     * Adds the value to X.
     * @param val the value
     */
    public void addValToX(int val) {
        this.xCord += val;
    }

    /**
     * Adds the value to Y.
     * @param val the value
     */
    public void addValToY(int val) {
        this.yCord += val;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(this.xCord, this.yCord, this.img);
    }

    /**
     * Gets the img.
     * @return the img
     */
    public Image getImg() {
        return this.img;
    }

    @Override
    public void timePassed(double dt) {
    }

}
