package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The Class KeyPressStoppableAnimation.
 * @author Eden Yefet
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private Animation animation;
    private KeyboardSensor keyboard;
    private String key;
    private boolean stop;

    /**
     * Instantiates a new key press stoppable animation.
     * @param keyboard the KeyboardSensor
     * @param key the key to end the animation
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboard = keyboard;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        boolean temp = false;
        if (this.stop) {
            temp = true;
            this.stop = false;
        }
        return temp;
    }
}
