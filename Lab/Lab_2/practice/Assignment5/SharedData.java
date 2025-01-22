package Assignment5;

public class SharedData {
    private final int[] fibonacciSequence;

    public SharedData(int size) {
        this.fibonacciSequence = new int[size];
    }

    public void setFibonacciNumber(int index, int value) {
        fibonacciSequence[index] = value;
    }

    public int[] getFibonacciSequence() {
        return fibonacciSequence;
    }
}
