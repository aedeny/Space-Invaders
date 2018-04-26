package spaceinvaders;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import biuoop.DrawSurface;
import geometricprimitives.Point;
import geometricprimitives.Rectangle;
import io.ParsingUtilities;
import main.Sprite;

/**
 * A Background for the level WideEasy.
 * @author Eden Yefet
 */
public class MenuBackground implements Sprite {
    private Block backgroundColor;
    private Image image;
    private int imageWidth;

    /**
     * Constructor.
     */
    public MenuBackground() {
        this.backgroundColor = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.BLACK);
        this.image = ParsingUtilities.getImage("background_images/spaceInvaders.png");
        this.imageWidth = ((BufferedImage) this.image).getWidth();
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.backgroundColor.drawOn(d);
        if (this.image != null) {
            d.drawImage(d.getWidth() / 2 - this.imageWidth / 2, 100, this.image);
        }

    }

    @Override
    public void timePassed(double dt) {
    }
}
