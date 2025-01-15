package Assignment2;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        // Khởi tạo danh sách số nguyên
        ArrayList<Integer> numbers = new ArrayList<>();

        // Nhập dữ liệu từ người dùng
        String inputLine = sc.nextLine(); // Đọc toàn bộ dòng
        String[] inputArray = inputLine.split(" "); // Tách chuỗi thành mảng các phần tử

        // Chuyển từng phần tử trong mảng thành số nguyên và thêm vào danh sách
        for (String str : inputArray) {
            numbers.add(Integer.parseInt(str)); // Chuyển chuỗi thành số nguyên
        }

        // Tạo đối tượng SharedData để lưu kết quả
        SharedData sharedData = new SharedData(0, 0, 0);

        // Tạo các luồng
        Thread avgThread = new Thread(new AverageCalculator(numbers, sharedData));
        Thread maxThread = new Thread(new MaximumCalculator(numbers, sharedData));
        Thread minThread = new Thread(new MinimumCalculator(numbers, sharedData));

        // Chạy các luồng
        avgThread.start();
        maxThread.start();
        minThread.start();

        // Chờ các luồng hoàn thành
        avgThread.join();
        maxThread.join();
        minThread.join();

        // Hiển thị kết quả
        System.out.println("The average value is " + sharedData.getAverage());
        System.out.println("The minimum value is " + sharedData.getMinimum());
        System.out.println("The maximum value is " + sharedData.getMaximum());
    }
}
