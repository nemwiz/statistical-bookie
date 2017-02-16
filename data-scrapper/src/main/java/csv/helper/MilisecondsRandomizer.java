package csv.helper;

import java.util.Random;

public class MilisecondsRandomizer {

    private Random random;
    private int max;
    private int min;

    public MilisecondsRandomizer(int max, int min) {
        this.random = new Random();
        this.max = max;
        this.min = min;
    }

    public int randomize() {
        return this.random.nextInt((this.max - this.min) + 1000) + this.min;
    }
}
