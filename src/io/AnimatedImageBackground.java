package io;

import java.awt.image.BufferedImage;

import biuoop.DrawSurface;
import main.Sprite;

/**
 * The Class ImageBackground.
 * @author Eden Yefet
 */
public class AnimatedImageBackground implements Sprite {
    private ImageBackground backgroundImage;
    private long time;
    private int dxCounter;
    private int dyCounter;
    private int imgWidth;
    private int imgHeight;
    private int dx;
    private int dy;

    /**
     * Instantiates a new image background.
     * @param backgroundImage the backgroundImage
     */
    public AnimatedImageBackground(ImageBackground backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.time = System.currentTimeMillis();
        this.dxCounter = 0;
        this.dyCounter = 0;
        this.imgWidth = ((BufferedImage) this.backgroundImage.getImg()).getWidth();
        this.imgHeight = ((BufferedImage) this.backgroundImage.getImg()).getHeight();
        this.dx = -1;
        this.dy = -1;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.backgroundImage.drawOn(d);
        if (System.currentTimeMillis() - this.time > 10) {
            this.backgroundImage.addValToX(this.dx);
            this.backgroundImage.addValToY(this.dy);
            this.time = System.currentTimeMillis();
        }
        this.dxCounter++;
        this.dyCounter++;
        if (this.dxCounter % (this.imgWidth / 2) == 0) {
            this.dx *= -1;
        }
        if (this.dyCounter % (this.imgHeight / 2) == 0) {
            this.dy *= -1;
        }

    }

    @Override
    public void timePassed(double dt) {
    }

}
