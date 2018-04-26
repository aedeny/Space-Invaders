package spaceinvaders;

import main.HitListener;

/**
 * Ball Remover listener.
 * @author Eden Yefet
 */
public class BallRemover implements HitListener {

    private GameLevel game;

    /**
     * Instantiates a new ball remover.
     * @param g GameLevel
     */
    BallRemover(GameLevel g) {
        this.game = g;
    }

    @Override
    public void hitEvent(Alien alien, Ball hitter) {
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);

    }

    @Override
    public void hitEvent(SpaceShip alien, Ball hitter) {
    }

}
