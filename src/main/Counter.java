package main;

/**
 * Counter class.
 * @author Eden Yefet
 */
public class Counter {
    private int currentCount;

    /**
     * Constructor.
     */
    public Counter() {
        this.currentCount = 0;
    }

    /**
     * Constructor.
     * @param initialCount The initial count for the counter
     */
    public Counter(int initialCount) {
        this.currentCount = initialCount;
    }

    /**
     * Decrease.
     * @param number Subtracts this number from the value of the counter
     */
    public void decrease(int number) {
        this.currentCount -= number;
    }

    /**
     * Gets the value.
     * @return The current counter value
     */
    public int getValue() {
        return this.currentCount;
    }

    /**
     * Increase.
     * @param number Adds this number to the value of the counter
     */
    public void increase(int number) {
        this.currentCount += number;
    }
}
