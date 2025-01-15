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
        int max = Integer.MIN_VALUE; // Khởi tạo giá trị lớn nhất là giá trị nhỏ nhất của Integer
        for (int num : numbers) {
            if (num > max) {
                max = num; // Cập nhật giá trị lớn nhất nếu tìm thấy số lớn hơn
            }
        }
        sharedData.setMaximum(max); // Ghi giá trị lớn nhất vào SharedData
    }
}
