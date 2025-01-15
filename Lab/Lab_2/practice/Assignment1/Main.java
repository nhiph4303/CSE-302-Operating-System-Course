package Assignment1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedData sharedData = new SharedData(0);
        int iterations = 1_000_000;

        // Phần a
        sharedData.reset();

        IncrementTask r1 = new IncrementTask(sharedData, iterations);
        DecrementTask r2 = new DecrementTask(sharedData, iterations);

        Thread t1 = new Thread(r1); 
        Thread t2 = new Thread(r2); 

        t1.start();
        t1.join();

        t2.start(); 
        t2.join();

        System.out.println("Part a - Value: " + sharedData.getValue());

        // Phần b

        sharedData.reset();

        t1 = new Thread(r1); 
        t2 = new Thread(r2); 

        t1.start();
        t2.start();

        t1.join(); 
        t2.join(); 

        System.out.println("Part b - Value: " + sharedData.getValue());

        // Tạo 100 thread tăng và giảm
        int numThreads = 100;

        // Phần a: Thực thi tuần tự
        sharedData.reset();
        List<Thread> threads = new ArrayList<>();

        // Tạo 100 luồng tăng
        for (int i = 0; i < numThreads; i++) {
            IncrementTask r3 = new IncrementTask(sharedData, iterations);
            threads.add(new Thread(r3));
        }

        // Chạy tất cả các luồng tăng
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }

        threads.clear(); // Xóa danh sách luồng cũ

        // Tạo 100 luồng giảm
        for (int i = 0; i < numThreads; i++) {
            DecrementTask r4 = new DecrementTask(sharedData, iterations);
            threads.add(new Thread(r4));
        }

        // Chạy tất cả các luồng giảm
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Part a - Value: " + sharedData.getValue());

        // Phần b: Thực thi song song
        sharedData.reset();
        threads.clear();

        // Tạo 100 luồng tăng và giảm song song
        for (int i = 0; i < numThreads; i++) {
            IncrementTask r3 = new IncrementTask(sharedData, iterations);
            threads.add(new Thread(r3));

            IncrementTask r4 = new IncrementTask(sharedData, iterations);
            threads.add(new Thread(r4));
        }

        // Chạy tất cả các luồng song song
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Part b - Value: " + sharedData.getValue());
    }
}
