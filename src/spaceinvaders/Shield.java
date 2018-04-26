package spaceinvaders;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometricprimitives.Point;
import geometricprimitives.Rectangle;
import main.HitListener;
import main.Sprite;

/**
 * The Class Shield.
 * @author Eden Yefet
 */
public class Shield implements Sprite {

    private Color color;
    private Point upperLeft;
    private int width;
    private int height;
    private List<Block> blocks;

    /**
     * Instantiates a new shield.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     * @param g         the g
     */
    Shield(Point upperLeft, int width, int height, Color color, GameLevel g) {
        this.color = color;
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.blocks = null;
    }

    /**
     * Creates the blocks.
     *
     * @param size the size
     * @param g    the g
     */
    void createBlocks(int size, GameLevel g) {
        HitListener blockRemover = new BlockRemover(g);
        List<Block> miniBlocks = new ArrayList<>();
        int currentX = (int) this.upperLeft.getX();
        int currentY = (int) this.upperLeft.getY();

        for (int i = 0; i < Math.floor(this.height / size); i++) {
            for (int j = 0; j < Math.floor(this.width / size); j++) {
                Rectangle rect = new Rectangle(new Point(currentX, currentY), size, size);

                Block b = new Block(rect, 1, this.color);
                miniBlocks.add(b);
                b.addToGame(g);
                b.addHitListener(blockRemover);
                currentX += size;
            }
            currentX = (int) this.upperLeft.getX();
            currentY += size;
        }
    }

    @Override public void drawOn(DrawSurface d) {
        for (Block b : this.blocks) {
            b.drawOn(d);
        }
    }

    @Override public void timePassed(double dt) {
        for (Block b : this.blocks) {
            b.timePassed(dt);
        }
    }
}
