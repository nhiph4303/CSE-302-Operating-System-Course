package Assignment3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to calculate all prime numbers less than or equal to it:");

        int limit = Integer.parseInt(sc.nextLine());
        if (limit < 2) {
            System.out.println("There are no prime numbers less than 2.");
            return;
        }

        SharedData sharedData = new SharedData(limit);
        PrimeCalculator primeThread = new PrimeCalculator(sharedData);

        primeThread.start();

        try {
            primeThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
}
