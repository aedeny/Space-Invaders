package spaceinvaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class HighScoresTable.
 * @author Eden Yefet
 */
public final class HighScoresTable implements Serializable {

    private static final long serialVersionUID = 1L;

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    /**
     * Load from file.
     * @param filename the filename
     * @return the high scores table
     * @throws IOException Signals that an I/O exception has occurred.
     */
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        ObjectInputStream is = null;
        HighScoresTable hst;
        try {
            is = new ObjectInputStream(new FileInputStream(filename));
            hst = (HighScoresTable) is.readObject();
        } catch (ClassNotFoundException | FileNotFoundException e) {
            hst = new HighScoresTable(5);
            hst.save(filename);
            return hst;
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return hst;
    }

    private List<ScoreInfo> table;

    private int size;



    // Create an empty high-scores table with the specified size.
    /**
     * Instantiates a new high scores table.
     * @param size the size
     */
    // The size means that the table holds up to size top scores.
    private HighScoresTable(int size) {
        this.table = new ArrayList<>();
        this.size = size;
    }

    /**
     * Adds a high-score to the list.
     * @param score A ScoreInfo to be added to the list
     */
    public void add(ScoreInfo score) {
        if (this.getRank(score.getScore()) <= this.size) {
            this.table.add(score);
            this.table.sort(Collections.reverseOrder());
        }

        // Removes entries higher than table's size
        while (this.table.size() > this.size) {
            this.table.remove(this.table.size() - 1);
        }
    }

    /**
     * Gets the high scores.
     * @return The current sorted high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.table;
    }

    /**
     * // return : The rank of the current score. where will it
     * // be on the list if added?
     * // Rank 1 means the score will be highest on the list.
     * // Rank `size` means the score will be lowest.
     * // Rank > `size` means the score is too low and will not
     * // be added to the list.
     * @param score the score
     * @return The rank of the current score
     */
    int getRank(int score) {
        ArrayList<ScoreInfo> tableCopy = new ArrayList<>(this.table);
        ScoreInfo rankCheck = new ScoreInfo("rankCheck", score);
        tableCopy.add(rankCheck);
        tableCopy.sort(Collections.reverseOrder());
        return tableCopy.indexOf(rankCheck) + 1;
    }


    /**
     * Saves table data to the specified file.
     * @param filename the filename
     * @throws IOException Signals that an I/O exception has occurred.
     */
    void save(File filename) throws IOException {
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(this);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * Returns table size.
     * @return the size of the table
     */
    int size() {
        return this.size;
    }

    @Override
    public String toString() {
        String text = "";
        for (ScoreInfo si : this.table) {
            text = text.concat(si.getName() + "\t" + Integer.toString(si.getScore()));
            if (this.table.size() - this.table.indexOf(si) > 1) {
                text = text.concat("\n");
            }
        }
        return text;

    }
}
