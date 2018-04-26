package tasks;

import java.util.List;

import animation.AnimationRunner;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import main.LevelInformation;
import spaceinvaders.GameFlow;
import spaceinvaders.HighScoresTable;

/**
 * The Class StartNewGame.
 * @author Eden Yefet
 */
public class StartNewGame implements Task<Void> {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private DialogManager dialog;
    private HighScoresTable hst;

    /**
     * Instantiates a new Start New Game task.
     * @param runner the Animation Runner
     * @param keyboard the Keyboard Sensor
     * @param dialog the Dialog Manager
     * @param hst the High Scores Table
     * @param levelsList the levels list
     */
    public StartNewGame(AnimationRunner runner, KeyboardSensor keyboard, DialogManager dialog, HighScoresTable hst,
            List<LevelInformation> levelsList) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.dialog = dialog;
        this.hst = hst;
    }

    @Override
    public Void run() {
        GameFlow gameFlow = new GameFlow(this.runner, this.keyboard, this.dialog, this.hst);
        gameFlow.runLevels();
        return null;
    }

}
