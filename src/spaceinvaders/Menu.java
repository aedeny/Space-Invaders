package spaceinvaders;

import animation.Animation;

/**
 * The Interface Menu.
 * @author Eden Yefet
 * @param <T> the generic type
 */
public interface Menu<T> extends Animation {

    /**
     * Adds the selection.
     * @param key the key
     * @param message the message
     * @param returnVal the return value
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets the status.
     * @return the status
     */
    T getStatus();

    /**
     * Reset.
     */
    void reset();
}
