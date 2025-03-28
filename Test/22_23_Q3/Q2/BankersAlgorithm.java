package Q2;

import java.util.*;

public class BankersAlgorithm {

    static int[] available = {3, 2, 1, 1};  // Tài nguyên có sẵn (A, B, C, D)

    public static void main(String[] args) {
        // Khởi tạo các tiến trình
        Process[] processes = new Process[5];
        processes[0] = new Process(0, new int[]{4, 0, 0, 1}, new int[]{6, 0, 1, 2});
        processes[1] = new Process(1, new int[]{1, 1, 0, 0}, new int[]{7, 5, 0, 1});
        processes[2] = new Process(2, new int[]{1, 2, 5, 4}, new int[]{2, 3, 6, 6});
        processes[3] = new Process(3, new int[]{0, 5, 3, 3}, new int[]{1, 6, 5, 3});
        processes[4] = new Process(4, new int[]{0, 2, 1, 2}, new int[]{1, 6, 5, 6});

        // Kiểm tra hệ thống có an toàn không?
        if (isSafe(processes)) {
            System.out.println("The system is in a safe state.");
        } else {
            System.out.println("The system is in an unsafe state.");
        }

        // Kiểm tra yêu cầu tài nguyên từ tiến trình P1
        int[] request = {0, 4, 2, 0};  // Yêu cầu từ tiến trình P1
        if (requestResources(processes[1], request)) {
            System.out.println("Request can be granted.");
        } else {
            System.out.println("Request cannot be granted.");
        }
    }

    // Kiểm tra hệ thống có an toàn không
    static boolean isSafe(Process[] processes) {
        int[] work = Arrays.copyOf(available, available.length);
        boolean[] finish = new boolean[processes.length];
        int completed = 0;

        while (completed < processes.length) {
            boolean found = false;

            // Tìm tiến trình chưa hoàn thành và có need nhỏ hơn hoặc bằng work
            for (int i = 0; i < processes.length; i++) {
                if (!finish[i]) {
                    boolean canComplete = true;
                    for (int j = 0; j < 4; j++) {
                        if (processes[i].getNeed()[j] > work[j]) {
                            canComplete = false;
                            break;
                        }
                    }

                    if (canComplete) {
                        // Cập nhật work với tài nguyên của tiến trình đã hoàn thành
                        for (int j = 0; j < 4; j++) {
                            work[j] += processes[i].getAllocation()[j];
                        }
                        finish[i] = true;
                        completed++;
                        found = true;
                        break;
                    }
                }
            }

            // Nếu không tìm được tiến trình có thể hoàn thành, hệ thống không an toàn
            if (!found) {
                return false;
            }
        }
        return true;
    }

    // Kiểm tra yêu cầu tài nguyên từ tiến trình
    static boolean requestResources(Process process, int[] request) {
        // Kiểm tra xem yêu cầu tài nguyên có hợp lệ không
        for (int i = 0; i < 4; i++) {
            if (request[i] > process.getNeed()[i]) {
                return false;  // Yêu cầu tài nguyên lớn hơn cần thiết
            }
            if (request[i] > available[i]) {
                return false;  // Yêu cầu tài nguyên vượt quá số tài nguyên có sẵn
            }
        }

        // Giả sử tài nguyên được cấp phát
        for (int i = 0; i < 4; i++) {
            available[i] -= request[i];
            process.getAllocation()[i] += request[i];
            process.getNeed()[i] -= request[i];
        }

        // Kiểm tra hệ thống có an toàn không sau khi cấp phát tài nguyên
        if (isSafe(new Process[] {process})) {
            return true;  // Nếu hệ thống vẫn an toàn, yêu cầu được cấp phát
        }

        // Nếu không an toàn, phục hồi lại trạng thái ban đầu
        for (int i = 0; i < 4; i++) {
            available[i] += request[i];
            process.getAllocation()[i] -= request[i];
            process.getNeed()[i] += request[i];
        }

        return false;  // Nếu không an toàn, yêu cầu không thể được cấp phát
    }
}


