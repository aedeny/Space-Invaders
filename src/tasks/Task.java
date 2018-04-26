package tasks;

/**
 * The Interface Task.
 * @author Eden Yefet
 * @param <T> the generic type
 */
public interface Task<T> {

    /**
     * Run.
     * @return the t
     */
    T run();
}
