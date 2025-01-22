package Assignment2;

public class SharedData {
    private double average = 0;
    private int maximum = 0;
    private int minimum = 0;

    public SharedData(double average, int maximum, int minimum) {
        this.average = average;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public synchronized double getAverage() {
        return average;
    }

    public synchronized void setAverage(double average) {
        this.average = average;
    }

    public synchronized int getMaximum() {
        return maximum;
    }

    public synchronized void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public synchronized int getMinimum() {
        return minimum;
    }

    public synchronized void setMinimum(int minimum) {
        this.minimum = minimum;
    }
}
