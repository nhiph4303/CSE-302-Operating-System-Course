package Q4;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloPi {
    static long pointsInCircle = 0; // Sử dụng long thay vì int
    static final int NUM_THREADS = 4; // Số lượng luồng
    static final int POINTS_PER_THREAD = 1_000_000; // Số điểm ngẫu nhiên mỗi luồng tạo ra
    static final long TOTAL_POINTS = (long) NUM_THREADS * POINTS_PER_THREAD; // Tổng số điểm

    public static void main(String[] args) throws InterruptedException {
        // Tạo một thread pool
        Thread[] threads = new Thread[NUM_THREADS];

        // Tạo các thread và thực thi
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new MonteCarloTask());
            threads[i].start();
        }

        // Đợi tất cả các thread hoàn thành
        for (Thread thread : threads) {
            thread.join();
        }

        // Tính giá trị của pi
        if (TOTAL_POINTS == 0) {
            System.out.println("Lỗi: Tổng số điểm không thể bằng 0.");
            return;
        }

        double piEstimate = 4.0 * pointsInCircle / TOTAL_POINTS;
        System.out.println("Tổng số điểm: " + TOTAL_POINTS);
        System.out.println("Số điểm trong hình tròn: " + pointsInCircle);
        System.out.println("Giá trị ước lượng của π: " + piEstimate);
        System.out.println("Sai số so với π thực tế: " + Math.abs(piEstimate - Math.PI));
    }

    static class MonteCarloTask implements Runnable {
        @Override
        public void run() {
            long localPointsInCircle = 0;

            for (int i = 0; i < POINTS_PER_THREAD; i++) {
                // Sử dụng ThreadLocalRandom để tạo số ngẫu nhiên
                double x = ThreadLocalRandom.current().nextDouble() * 2 - 1; // [-1, 1]
                double y = ThreadLocalRandom.current().nextDouble() * 2 - 1; // [-1, 1]

                if (x * x + y * y <= 1) { // Kiểm tra nếu điểm (x, y) nằm trong hình tròn
                    localPointsInCircle++;
                }
            }

            // Cập nhật số điểm trong hình tròn một cách thread-safe
            synchronized (MonteCarloPi.class) {
                pointsInCircle += localPointsInCircle;
            }
        }
    }
}