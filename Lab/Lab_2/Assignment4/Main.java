package Assignment4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the total number of random points to generate:");

        int totalPoints = sc.nextInt();
        System.out.println("Enter the number of threads to use:");
        int threadCount = sc.nextInt();

        if (totalPoints <= 0 || threadCount <= 0) {
            System.out.println("Please enter positive values for the number of points and threads.");
            return;
        }

        SharedData sharedData = new SharedData();
        MonteCarloThread[] threads = new MonteCarloThread[threadCount];

        int pointsPerThread = totalPoints / threadCount;
        int remainingPoints = totalPoints % threadCount;

        for (int i = 0; i < threadCount; i++) {
            int points = (i == threadCount - 1) ? pointsPerThread + remainingPoints : pointsPerThread;
            threads[i] = new MonteCarloThread(points, sharedData);
            threads[i].start();
        }

        for (MonteCarloThread thread : threads) {
            thread.join();
        }

        double estimatedPi = 4.0 * sharedData.getPointsInCircle() / totalPoints;
        System.out.printf("Estimated value of π: %.6f%n", estimatedPi);
    }
}
