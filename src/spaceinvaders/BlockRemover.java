package spaceinvaders;

import main.HitListener;

/**
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author Eden Yefet
 */
public class BlockRemover implements HitListener {
    private GameLevel game;

    /**
     * Constructor.
     * @param g GameLevel
     */
    BlockRemover(GameLevel g) {
        this.game = g;
    }

    @Override
    public void hitEvent(Alien alien, Ball hitter) {
        if (hitter.isFriendly()) {
            this.game.getAliensCollection().removeAlien(alien);
            alien.removeFromGame(this.game);
            alien.removeHitListener(this);
        }
        hitter.removeFromGame(this.game);
    }

    /**
     * Removes blocks that are hit and reach 0 hit-points from the game.
     * @param beingHit The block being hit.
     * @param hitter The Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        beingHit.removeHitListener(this);

        beingHit.removeFromGame(this.game);
    }

    @Override
    public void hitEvent(SpaceShip spaceShip, Ball hitter) {
        spaceShip.removeHitListener(this);
        this.game.kill();

    }
}
