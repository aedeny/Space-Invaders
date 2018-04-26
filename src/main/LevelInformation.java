package main;

import java.awt.Color;
import java.util.List;

import spaceinvaders.Block;

/**
 * This interface is responsible for the information required for a level.
 * @author Eden Yefet
 */
public interface LevelInformation {

    /**
     * Ball color.
     * @return The color of the ball
     */
    Color ballColor();

    /**
     * Blocks.
     * @return A list of the blocks that make up this level.
     */
    List<Block> blocks();

    /**
     * Gets the background.
     * @return A sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * Level name.
     * @return The name of the level.
     */
    String levelName();

    /**
     * Number of blocks to remove.
     * @return The initial number of blocks that should be removed from the game.
     */
    int numberOfAliensToRemove();

    /**
     * Paddle color.
     * @return The color of the paddle
     */
    Color paddleColor();

    /**
     * Paddle speed.
     * @return This paddle speed.
     */
    int paddleSpeed();

    /**
     * Paddle width.
     * @return This paddle width.
     */
    int paddleWidth();

    /**
     * Text on screen color.
     * @return Color for text which should be displayed on screen
     */
    Color textOnScreenColor();

}
