package spaceinvaders;

import java.io.File;
import java.io.IOException;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import main.Counter;

/**
 * This class is responsible for the game flow of the game.
 * @author Eden Yefet
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter numOfLives;
    private Counter score;
    private HighScoresTable table;
    private DialogManager dialog;
    private double levelSpeed;

    /**
     * Constructor.
     * @param ar Animation Runner
     * @param ks KeyboardSensor
     * @param dialog the dialog
     * @param table the table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, DialogManager dialog, HighScoresTable table) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.numOfLives = new Counter(3);
        this.score = new Counter();
        this.dialog = dialog;
        this.table = table;
        this.levelSpeed = 50;

    }

    /**
     * Adds the player to the high scores table.
     */
    private void addPlayer() {
        if (this.table.getRank(this.score.getValue()) <= this.table.size()) {
            String name = this.dialog.showQuestionDialog("Enter your name", "Name", "");
            this.table.add(new ScoreInfo(name, this.score.getValue()));
            try {
                this.table.save(new File("highscores.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Runs the end screen.
     */
    private void runEndScreen() {
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.score)));
    }

    /**
     * Runs the high scores table.
     */
    private void runHighScoreTable() {
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.table)));
    }

    /**
     * Runs the levels until all of the levels are cleared or until there are no more lives left.
     */
    public void runLevels() {
        int levelID = 1;
        while (true) {
            GameLevel level = new GameLevel(this.keyboardSensor, this.animationRunner, this.numOfLives, this.score,
                    this.levelSpeed, levelID);
            level.initialize();

            while (level.getRemainedBlocks().getValue() > 0 && level.getNumOfLives().getValue() > 0) {
                level.playOneTurn();
            }

            if (level.getNumOfLives().getValue() <= 0) {
                break;
            } else {
                this.score.increase(100);
                this.levelSpeed *= 1.2;
                levelID++;
            }
        }
        this.runEndScreen();
        this.addPlayer();
        this.runHighScoreTable();

    }
}
