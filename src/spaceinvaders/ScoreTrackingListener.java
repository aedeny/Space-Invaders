package spaceinvaders;

import main.Counter;
import main.HitListener;

/**
 * This class is in charge on tracking the score.
 * @author Eden Yefet
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter Score counter
     */
    ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Alien alien, Ball hitter) {
        this.currentScore.increase(100);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }

    @Override
    public void hitEvent(SpaceShip alien, Ball hitter) {
    }
}
