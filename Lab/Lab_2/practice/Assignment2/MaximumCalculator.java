package Assignment2;

import java.util.ArrayList;

public class MaximumCalculator implements Runnable {
    private ArrayList<Integer> numbers;
    private SharedData sharedData;

    public MaximumCalculator(ArrayList numbers, SharedData sharedData) {
        this.numbers = numbers;
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        int max = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (num > max) {
                max = num; 
            }
        }
        sharedData.setMaximum(max); 
    }
}
