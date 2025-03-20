package Assignment5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Fibonacci numbers to generate:");

        int count = sc.nextInt();

        if (count <= 0) {
            System.out.println("Please enter a positive number.");
            return;
        }

        SharedData sharedData = new SharedData(count);
        FibonacciThread fibonacciThread = new FibonacciThread(sharedData);

        fibonacciThread.start();

        try {
            fibonacciThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        System.out.println("Generated Fibonacci sequence:");
        int[] sequence = sharedData.getFibonacciSequence();
        for (int num : sequence) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
