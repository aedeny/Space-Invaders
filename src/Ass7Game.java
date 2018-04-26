
import java.io.File;
import java.io.IOException;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import spaceinvaders.HighScoresTable;
import spaceinvaders.Menu;
import tasks.QuitTask;
import tasks.ShowHiScoresTask;
import tasks.StartNewGame;
import tasks.Task;

/**
 * The Main class.
 * Creates a Game instance and runs the game.
 * @author Eden Yefet
 */
public class Ass7Game {
    /**
     * Main method.
     * @param args n/a
     */
    public static void main(String[] args) {

        // Load or create HighScoresTable
        HighScoresTable hst = null;
        try {
            hst = HighScoresTable.loadFromFile(new File("highscores.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GUI gui = new GUI("Space Invaders", 800, 600);
        DialogManager dialog = gui.getDialogManager();
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        // Create Main Menu
        Menu<Task<Void>> mainMenu = new MenuAnimation<>(keyboard, ar);
        mainMenu.addSelection("s", "Start Game", new StartNewGame(ar, keyboard, dialog, hst, null));
        mainMenu.addSelection("h", "High Scores", new ShowHiScoresTask(ar,
                new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new HighScoresAnimation(hst))));
        mainMenu.addSelection("q", "Quit Game", new QuitTask());

        // Run game
        while (true) {
            ar.run(mainMenu);
            Task<Void> task = mainMenu.getStatus();
            task.run();
            mainMenu.reset();
        }
    }
}
