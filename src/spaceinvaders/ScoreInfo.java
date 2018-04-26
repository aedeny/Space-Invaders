package spaceinvaders;

import java.io.Serializable;

/**
 * The Class ScoreInfo.
 * @author Eden Yefet
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    /**
     * Instantiates a new score info.
     * @param name the name
     * @param score the score
     */
    ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(ScoreInfo other) {
        if (this.score > other.score) {
            return 1;
        } else if (this.score < other.score) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the score.
     * @return the score
     */
    public int getScore() {
        return this.score;
    }
}
