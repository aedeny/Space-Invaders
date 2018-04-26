package main;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class LevelSets.
 * @author Eden Yefet
 */
public class LevelSets {
    /**
     * The Class LevelSet.
     */
    public static class LevelSet {
        private String message;
        private String key;
        private String levelDefinitionPath;

        /**
         * Gets the key.
         * @return the key
         */
        public String getKey() {
            return this.key;
        }

        /**
         * Gets the level definition path.
         * @return the level definition path
         */
        public String getLevelDefinitionPath() {
            return this.levelDefinitionPath;
        }

        /**
         * Gets the message.
         * @return the message
         */
        public String getMessage() {
            return this.message;
        }

        /**
         * Sets the key.
         * @param k the new key
         */
        public void setKey(String k) {
            this.key = k;
        }

        /**
         * Sets the level definition path.
         * @param lvlDefinitionPath the new level definition path
         */
        public void setLevelDefinitionPath(String lvlDefinitionPath) {
            this.levelDefinitionPath = lvlDefinitionPath;
        }

        /**
         * Sets the message.
         * @param msg the new message
         */
        public void setMessage(String msg) {
            this.message = msg;
        }

    }

    /**
     * From reader.
     * @param reader the reader
     * @return the level sets
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static LevelSets fromReader(Reader reader) throws IOException {
        LevelSets result = new LevelSets();

        LevelSet currentSet = null;
        LineNumberReader lineReader = null;
        try {
            lineReader = new LineNumberReader(reader);

            String line = null;
            while ((line = lineReader.readLine()) != null) {
                if (lineReader.getLineNumber() % 2 == 0) {
                    currentSet.setLevelDefinitionPath(line.trim());

                    result.addLevelSet(currentSet);
                    currentSet = null;
                } else {
                    currentSet = new LevelSet();

                    String[] lineArgs = line.trim().split(":");
                    currentSet.setKey(lineArgs[0]);
                    currentSet.setMessage(lineArgs[1]);
                }
            }
        } finally {
            if (lineReader != null) {
                lineReader.close();
            }
        }
        return result;
    }

    private List<LevelSet> levelSetList;

    /**
     * Instantiates a new level sets.
     */
    public LevelSets() {
        this.levelSetList = new ArrayList<LevelSet>();
    }

    /**
     * Adds the level set.
     * @param levelSet the level set
     */
    public void addLevelSet(LevelSet levelSet) {
        this.levelSetList.add(levelSet);
    }

    /**
     * Gets the level set list.
     * @return the level set list
     */
    public List<LevelSet> getLevelSetList() {
        return this.levelSetList;
    }
}
