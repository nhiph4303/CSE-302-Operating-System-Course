package Assignment2;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter numbers separated by spaces:");
        String inputLine = sc.nextLine();

        ArrayList<Integer> numbers = new ArrayList<>();
        String[] inputArray = inputLine.split(" ");
            for (String str : inputArray) {
                numbers.add(Integer.parseInt(str));
            }
    

       

        SharedData sharedData = new SharedData(0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        Thread avgThread = new Thread(new AverageCalculator(numbers, sharedData));
        Thread maxThread = new Thread(new MaximumCalculator(numbers, sharedData));
        Thread minThread = new Thread(new MinimumCalculator(numbers, sharedData));

        avgThread.start();
        maxThread.start();
        minThread.start();

        avgThread.join();
        maxThread.join();
        minThread.join();

        System.out.println("The average value is " + sharedData.getAverage());
        System.out.println("The minimum value is " + sharedData.getMinimum());
        System.out.println("The maximum value is " + sharedData.getMaximum());
    }
}
