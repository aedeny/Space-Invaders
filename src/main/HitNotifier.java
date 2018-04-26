package main;

/**
 * This interface is responsible for notifying the listeners about a hit.
 * @author Eden Yefet
 */
public interface HitNotifier {

    /**
     * Adds a hit listener to the notifier.
     * @param hl A Hit Listener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a Hit Listener.
     * @param hl A Hit Listener to be removed.
     */
    void removeHitListener(HitListener hl);
}
