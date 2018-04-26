package tasks;

import animation.Animation;
import animation.AnimationRunner;

/**
 * The Class ShowHiScoresTask.
 * @author Eden Yefet
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Instantiates a new show hi scores task.
     * @param runner the runner
     * @param highScoresAnimation the high scores animation
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * Runs the animation.
     * @return null
     */
    @Override
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
