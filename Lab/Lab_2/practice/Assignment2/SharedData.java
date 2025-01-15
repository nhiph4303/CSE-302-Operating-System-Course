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

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    

    
    
}
