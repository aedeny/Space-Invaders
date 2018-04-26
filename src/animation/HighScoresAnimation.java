package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import spaceinvaders.HighScoresTable;
import spaceinvaders.ScoreInfo;

/**
 * The Class HighScoresAnimation.
 * @author Eden Yefet
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * Instantiates a new high scores animation.
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // "High Scores"
        d.setColor(new Color(255, 169, 15));
        d.drawText(150, 100, "HIGH SCORES", 60);
        d.setColor(new Color(254, 235, 176));
        d.drawText(153, 100, "HIGH SCORES", 60);

        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            ScoreInfo si = this.scores.getHighScores().get(i);
            d.setColor(new Color(238, 36, 59));
            d.drawText(150, 140 + 40 * i, si.getName(), 30);
            d.setColor(new Color(254, 233, 0));
            d.drawText(500, 140 + 40 * i, Integer.toString(si.getScore()), 30);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
