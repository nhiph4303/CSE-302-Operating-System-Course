// Main.java
package Q1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo danh sách tiến trình
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 0, 11, 2));
        processes.add(new Process("P2", 5, 28, 0));
        processes.add(new Process("P3", 12, 2, 3));
        processes.add(new Process("P4", 2, 10, 1));
        processes.add(new Process("P5", 9, 16, 4));

        // Tạo đối tượng scheduler và chạy thuật toán
        PreemptivePriorityScheduler scheduler = new PreemptivePriorityScheduler(processes);
        scheduler.schedule();
    }
}