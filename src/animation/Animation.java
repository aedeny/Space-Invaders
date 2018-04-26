package animation;

import biuoop.DrawSurface;

/**
 * An interface for animations.
 * @author Eden Yefet
 */
public interface Animation {

    /**
     * "Plays" one frame of the animation.
     * @param d DrawSurface
     * @param dt the dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Should stop.
     * @return True if should stop, false otherwise.
     */
    boolean shouldStop();
}
