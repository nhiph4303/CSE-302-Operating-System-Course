package Assignment2;

import java.util.ArrayList;

public class AverageCalculator implements Runnable {
    private ArrayList<Integer> numbers;
    private SharedData sharedData;

    public AverageCalculator(ArrayList numbers, SharedData sharedData) {
        this.numbers = numbers;
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        double sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double avg = sum / numbers.size();
        sharedData.setAverage(avg); 
    }
}
