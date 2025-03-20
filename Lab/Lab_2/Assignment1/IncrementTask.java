package Assignment1;

public class IncrementTask implements Runnable {
    private SharedData sharedData;
    private int iterations;

    public IncrementTask(SharedData sharedData, int iterations) {
        this.sharedData = sharedData;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            sharedData.increment();
        }
    }
}

