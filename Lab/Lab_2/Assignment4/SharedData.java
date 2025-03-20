package Assignment4;

public class SharedData {
    private int pointsInCircle;

    public SharedData() {
        this.pointsInCircle = 0;
    }

    public synchronized void incrementPointsInCircle() {
        pointsInCircle++;
    }

    public int getPointsInCircle() {
        return pointsInCircle;
    }
}
