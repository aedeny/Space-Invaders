package main;

import spaceinvaders.Alien;
import spaceinvaders.Ball;
import spaceinvaders.Block;
import spaceinvaders.SpaceShip;

/**
 * Hit Listener.
 * @author Eden Yefet
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param alien the alien
     * @param hitter the hitter
     */
    void hitEvent(Alien alien, Ball hitter);

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit The block being hit.
     * @param hitter The Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);

    /**
     * This method is called whenever the beingHit object is hit.
     * @param alien the alien
     * @param hitter the hitter
     */
    void hitEvent(SpaceShip alien, Ball hitter);

}
