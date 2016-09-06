/**
 * Created by kurt on 6/09/2016.
 */
public class Counter {
    private final int max;
    private int count;

    public Counter(int max) {
        if (max < 1) { throw new IllegalArgumentException(); }

        this.max = max;
    }

    public Counter(int max, int seed){
        this(max);
        this.count = seed;
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized int increment() {
        count = (count + 1) % max;
        return count;
    }
}