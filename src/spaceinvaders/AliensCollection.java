package spaceinvaders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import biuoop.DrawSurface;
import main.Counter;
import main.Sprite;

/**
 * The Class AliensCollection.
 * @author Eden Yefet
 */
public class AliensCollection implements Sprite {
    private Counter numOfAliens;
    private List<Alien> aliensCollection;
    private int leftBound;
    private int rightBound;
    private GameLevel game;
    private long currentTime;
    private double initialLevelSpeed;
    private int bottomBound;

    /**
     * Instantiates a new aliens collection.
     *
     * @param numOfAliens       the num of aliens
     * @param leftBound         the left bound
     * @param rightBound        the right bound
     * @param bottomBound       the bottom bound
     * @param game              the game
     * @param initialLevelSpeed the initial level speed
     */
    AliensCollection(Counter numOfAliens, int leftBound, int rightBound, int bottomBound, GameLevel game,
            double initialLevelSpeed) {
        this.aliensCollection = new ArrayList<>();
        this.numOfAliens = numOfAliens;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.game = game;
        this.currentTime = System.currentTimeMillis();
        this.initialLevelSpeed = initialLevelSpeed;
        this.bottomBound = bottomBound;

    }

    /**
     * Adds the alien.
     *
     * @param a the a
     */
    void addAlien(Alien a) {
        this.aliensCollection.add(a);
        this.numOfAliens.increase(1);
    }

    /**
     * Change direction for all.
     */
    private void changeDirectionForAll() {
        for (Alien a : this.aliensCollection) {
            a.changeDirection();
        }
    }

    @Override public void drawOn(DrawSurface d) {
        List<Alien> aliens = new ArrayList<>(this.aliensCollection);
        for (Alien a : aliens) {
            a.drawOn(d);
        }
    }

    /**
     * Move one step.
     */
    void moveOneStep() {
        if (this.reachedBottom()) {
            this.game.kill();
        }
        for (Alien a : this.aliensCollection) {
            if (a.reachedLimits(this.leftBound, this.rightBound)) {
                this.changeDirectionForAll();
                return;
            }
        }
        if (System.currentTimeMillis() - this.currentTime > 500 && this.aliensCollection.size() > 0) {
            this.currentTime = System.currentTimeMillis();
            this.shootRandomly();
        }

    }

    /**
     * Pick A random column.
     *
     * @return the integer
     */
    private Integer pickARandomColumn() {
        List<Alien> aliens = new ArrayList<>(this.aliensCollection);
        Set<Integer> columns = new HashSet<>();

        for (Alien a : aliens) {
            columns.add(a.getColumn());
        }

        int size = columns.size();
        int item = new Random().nextInt(size);
        int j = 0;
        for (Integer i : columns) {
            if (item == j) {
                return i;
            } else {
                j++;
            }
        }
        return null;
    }

    /**
     * Checks if some alien has reached the shield's level.
     *
     * @return true, if successful
     */
    private boolean reachedBottom() {
        for (Alien a : this.aliensCollection) {
            if ((int) a.getCenter().getY() > this.bottomBound) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the alien.
     *
     * @param a the a
     */
    void removeAlien(Alien a) {
        this.aliensCollection.remove(a);
        this.numOfAliens.decrease(1);
    }

    /**
     * Returns all aliens to start.
     *
     * @param x the x
     * @param y the y
     */
    void returnAliensToStart(int x, int y) {
        List<Alien> aliens = new ArrayList<>(this.aliensCollection);
        int minRow = 100;
        int minColumn = 100;
        int xOffset = 0;
        int yOffset = 0;
        for (Alien a : aliens) {
            if (a.getRow() < minRow) {
                minRow = a.getRow();
                yOffset = y - (int) a.getPosition().getY();
            }
            if (a.getColumn() < minColumn) {
                minColumn = a.getColumn();
                xOffset = x - (int) a.getPosition().getX();
            }
        }
        for (Alien a : aliens) {
            a.setSpeed(this.initialLevelSpeed);
            a.setPosition((int) a.getPosition().getX() + xOffset, (int) a.getPosition().getY() + yOffset);
        }
    }

    /**
     * Picks a random column and make the lowest alien shoot down.
     */
    private void shootRandomly() {
        List<Alien> aliens = new ArrayList<>(this.aliensCollection);
        Integer randomColumn;
        randomColumn = this.pickARandomColumn();
        if (randomColumn == null) {
            return;
        }

        Alien lowestInColumn = null;
        for (Alien a : aliens) {
            if (a.getColumn() == randomColumn && (lowestInColumn == null || a.getRow() > lowestInColumn.getRow())) {
                lowestInColumn = a;
            }
        }
        lowestInColumn.shoot(this.game);
    }

    @Override public void timePassed(double dt) {
        List<Alien> aliens = new ArrayList<>(this.aliensCollection);
        for (Alien a : aliens) {
            a.timePassed(dt);
        }
    }
}
