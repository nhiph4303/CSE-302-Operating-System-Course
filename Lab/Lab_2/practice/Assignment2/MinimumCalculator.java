package Assignment2;

import java.util.ArrayList;

public class MinimumCalculator implements Runnable {
    private ArrayList<Integer> numbers;
    private SharedData sharedData;

    public MinimumCalculator(ArrayList numbers, SharedData sharedData) {
        this.numbers = numbers;
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        int min = Integer.MAX_VALUE; // Khởi tạo giá trị nhỏ nhất
        for (int num : numbers) {
            if (num < min) {
                min = num; // Cập nhật giá trị nhỏ nhất nếu tìm thấy số nhỏ hơn
            }
        }
        sharedData.setMinimum(min); // Ghi giá trị nhỏ nhất vào SharedData
    }
}
