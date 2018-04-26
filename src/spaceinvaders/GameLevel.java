package spaceinvaders;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometricprimitives.Point;
import geometricprimitives.Rectangle;
import io.AnimatedImageBackground;
import io.ImageBackground;
import io.ParsingUtilities;
import main.Collidable;
import main.Counter;
import main.HitListener;
import main.Sprite;

/**
 * This is the Game class.
 * The game class joins together the different game objects, initializes the game and runs it.
 *
 * @author Eden Yefet
 */
public class GameLevel implements Animation {
    // Window & Appearance Defaults
    public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
    static final int BORDERS_SIZE = 10;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AliensCollection aliensCollection;
    private KeyboardSensor keyboard;
    private Counter remainedAliens;
    private Counter score;
    private Counter numOfLives;
    private AnimationRunner runner;
    private boolean running;
    private double initialLevelSpeed;
    private String levelTitle;
    private List<Ball> activeShots;
    private SpaceShip spaceShip;
    private Point initialPaddleLocation;

    /**
     * Constructor.
     *
     * @param ks         KeyboardSensor
     * @param ar         AnimationRunner
     * @param numOfLives Number of lives counter
     * @param score      Scores counter
     * @param levelSpeed the level speed
     * @param levelID    the level ID
     */
    public GameLevel(KeyboardSensor ks, AnimationRunner ar, Counter numOfLives, Counter score, double levelSpeed,
            int levelID) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.keyboard = ks;
        this.remainedAliens = new Counter();
        this.score = score;
        this.numOfLives = numOfLives;
        this.runner = ar;
        this.spaceShip = null;
        this.initialPaddleLocation = new Point(375, 530);
        this.initialLevelSpeed = levelSpeed;
        this.aliensCollection = new AliensCollection(this.remainedAliens, 30, 770, 500, this, this.initialLevelSpeed);
        this.levelTitle = "Battle #" + levelID;
        this.activeShots = new ArrayList<>();
    }

    /**
     * Adds a Collidable object to the game.
     *
     * @param c A Collidable object to be added.
     */
    void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a Sprite object to the game.
     *
     * @param s A Sprite object.
     */
    void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Creates a paddle.
     */
    private void createSpaceShip() {
        HitListener blockRemover = new BlockRemover(this);
        if (this.spaceShip != null) {
            this.spaceShip.removeFromGame(this);
        }
        this.spaceShip =
                new SpaceShip(new Rectangle(this.initialPaddleLocation, 50, 20), this.keyboard, 500, this);
        this.spaceShip.addToGame(this);
        this.spaceShip.addHitListener(blockRemover);
    }

    @Override public void doOneFrame(DrawSurface d, double dt) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.sprites, Color.WHITE)));
            this.runner.run(new CountdownAnimation(2, 3, this.sprites, Color.WHITE));
        }

        if (this.remainedAliens.getValue() <= 0) {
            this.running = false;
        }
        this.aliensCollection.moveOneStep();
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

    }

    /**
     * Gets the active shots.
     *
     * @return the activeShots
     */
    List<Ball> getActiveShots() {
        return this.activeShots;
    }

    /**
     * Gets the aliens collection.
     *
     * @return the aliens collection
     */
    AliensCollection getAliensCollection() {
        return this.aliensCollection;
    }

    /**
     * Gets the environment.
     *
     * @return the environment
     */
    GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Gets the num of lives.
     *
     * @return Remaining lives Counter
     */
    Counter getNumOfLives() {
        return this.numOfLives;
    }

    /**
     * Gets the remained blocks.
     *
     * @return Remaining blocks Counter
     */
    Counter getRemainedBlocks() {
        return this.remainedAliens;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add
     * them to the game.
     */
    void initialize() {
        this.sprites.addSprite(new AnimatedImageBackground(
                new ImageBackground(ParsingUtilities.getImage("background_images/space.jpg"))));
        this.initializeBorderBlocks();
        this.initializeDeathBlock();

        this.sprites.addSprite(this.aliensCollection);
        this.initializeAliens();
        this.initializeIndicators();
        this.initializeShields();
    }

    /**
     * Initialize aliens.
     */
    private void initializeAliens() {
        List<Image> alienImages = new ArrayList<>();
        alienImages.add(ParsingUtilities.getImage("block_images/alien11.png"));
        alienImages.add(ParsingUtilities.getImage("block_images/alien22.png"));
        HitListener blockRemover = new BlockRemover(this);
        HitListener scoreListener = new ScoreTrackingListener(this.score);
        HitListener ballRemover = new BallRemover(this);
        int currentX = 40;
        int currentY = 80;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 10; c++) {
                Alien a = new Alien(currentX, currentY, alienImages, this.initialLevelSpeed, r, c);
                this.aliensCollection.addAlien(a);
                a.addToGame(this);
                a.addHitListener(blockRemover);
                a.addHitListener(scoreListener);
                a.addHitListener(ballRemover);
                currentX += 50;
            }
            currentY += 40;
            currentX = 40;
        }

    }

    /**
     * Initialize border blocks.
     */
    private void initializeBorderBlocks() {
        HitListener ballRemover = new BallRemover(this);
        // Border Blocks
        Block up = new Block(new Rectangle(new Point(0, 30), WINDOW_WIDTH, BORDERS_SIZE), Color.DARK_GRAY);
        up.addToGame(this);
        up.addHitListener(ballRemover);

        Block right =
                new Block(new Rectangle(new Point(WINDOW_WIDTH - BORDERS_SIZE, 30), BORDERS_SIZE, WINDOW_HEIGHT - 30),
                        Color.DARK_GRAY);
        right.addToGame(this);
        right.addHitListener(ballRemover);

        Block left = new Block(new Rectangle(new Point(0, 30), BORDERS_SIZE, WINDOW_HEIGHT - 30), Color.DARK_GRAY);
        left.addToGame(this);
        left.addHitListener(ballRemover);
        // Death Region Block

    }

    /**
     * Initialize death block.
     */
    private void initializeDeathBlock() {
        HitListener ballRemover = new BallRemover(this);
        Block death = new Block(new Rectangle(new Point(0, WINDOW_HEIGHT + BORDERS_SIZE), WINDOW_WIDTH, BORDERS_SIZE),
                Color.DARK_GRAY);
        death.addToGame(this);
        death.addHitListener(ballRemover);
    }

    /**
     * Initialize indicators.
     */
    private void initializeIndicators() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.numOfLives);
        livesIndicator.addToGame(this);
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(this.levelTitle);
        levelNameIndicator.addToGame(this);

    }

    /**
     * Initialize shields.
     */
    private void initializeShields() {
        for (int i = 0; i < 3; i++) {
            Shield shield = new Shield(new Point(75 + i * 250, 500), 150, 20, Color.CYAN, this);
            shield.createBlocks(5, this);
        }
    }

    /**
     * Kill.
     */
    void kill() {
        this.aliensCollection.returnAliensToStart(50, 80);
        List<Ball> shots = new ArrayList<>(this.activeShots);
        for (Ball b : shots) {
            b.removeFromGame(this);
        }
        this.numOfLives.decrease(1);
        this.running = false;

    }

    /**
     * Runs the game, starts the animation loop.
     */
    void playOneTurn() {
        this.createSpaceShip();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, Color.WHITE));
        this.running = true;
        this.runner.run(this);

    }

    /**
     * Removes a collidable object from the game.
     *
     * @param c A Collidable object to be removed.
     */
    void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s A sprite to remove
     */
    void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    @Override public boolean shouldStop() {
        return !this.running;
    }

}
