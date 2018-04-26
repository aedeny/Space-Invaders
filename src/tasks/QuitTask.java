package tasks;

/**
 * The Class QuitTask.
 * @author Eden Yefet
 */
public class QuitTask implements Task<Void> {

    /**
     * Instantiates a new quit task.
     */
    public QuitTask() {

    }

    @Override
    public Void run() {
        System.exit(0);
        return null;
    }

}
