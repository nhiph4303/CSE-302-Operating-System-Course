package Assignment4;

import java.util.Random;

public class MonteCarloThread extends Thread {
    private final int totalPoints;
    private final SharedData sharedData;

    public MonteCarloThread(int totalPoints, SharedData sharedData) {
        this.totalPoints = totalPoints;
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        Random random = new Random();
        int pointsInCircle = 0;

        for (int i = 0; i < totalPoints; i++) {
            double x = random.nextDouble() * 2 - 1; 
            double y = random.nextDouble() * 2 - 1; 

            if (x * x + y * y <= 1) { 
                pointsInCircle++;
            }
        }

        for (int i = 0; i < pointsInCircle; i++) {
            sharedData.incrementPointsInCircle();
        }
    }
}
