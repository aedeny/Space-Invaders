package spaceinvaders;

/**
 * The Class MenuSelection.
 * @author Eden Yefet
 * @param <T> the generic type
 */
public class MenuSelection<T> {
    private String key;
    private String option;
    private T returnVal;
    private boolean isSubMenu;

    /**
     * Instantiates a new menu selection.
     * @param key the key
     * @param option the option
     * @param returnVal the return value
     * @param isSubMenu the is sub menu
     */
    public MenuSelection(String key, String option, T returnVal, boolean isSubMenu) {
        this.key = key;
        this.option = option;
        this.returnVal = returnVal;
        this.isSubMenu = isSubMenu;
    }

    /**
     * Gets the key.
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Gets the option.
     * @return the option
     */
    public String getOption() {
        return this.option;
    }

    /**
     * Gets the return value.
     * @return the return value
     */
    public T getReturnVal() {
        return this.returnVal;
    }

    /**
     * Checks if is sub menu.
     * @return true, if is sub menu
     */
    public boolean isSubMenu() {
        return this.isSubMenu;
    }
}
