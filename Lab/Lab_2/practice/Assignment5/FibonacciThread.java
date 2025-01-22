package Assignment5;

public class FibonacciThread extends Thread {
    private final SharedData sharedData;

    public FibonacciThread(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        int[] sequence = sharedData.getFibonacciSequence();
        if (sequence.length > 0) {
            sequence[0] = 0; 
        }
        if (sequence.length > 1) {
            sequence[1] = 1; 
        }

        for (int i = 2; i < sequence.length; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }
    }
}
