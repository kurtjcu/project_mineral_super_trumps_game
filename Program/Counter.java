/**
 * Created by kurt on 6/09/2016.
 * <p>
 * Copied from http://stackoverflow.com/questions/7572434/how-to-implement-a-concurrent-circular-ticker-counter-in-java
 * <p>
 * Added seed for occasions where you do not want to start at first item.
 */


class Counter {
    private final int max;
    private int count;

    private Counter(int max) {
        if (max < 1) {
            throw new IllegalArgumentException();
        }

        this.max = max;
    }

    Counter(int max, int seed) {
        this(max);
        this.count = seed;
    }

    int getCount() {
        return count;
    }

    int increment() {
        count = (count + 1) % max;
        return count;
    }
}