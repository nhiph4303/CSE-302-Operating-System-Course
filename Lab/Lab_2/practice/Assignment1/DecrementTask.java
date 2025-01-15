package Assignment1;

public class DecrementTask implements Runnable {
    private SharedData sharedData;
    private int iterations;

    public DecrementTask(SharedData sharedData, int iterations) {
        this.sharedData = sharedData;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            sharedData.decrement();
        }
    }
}
