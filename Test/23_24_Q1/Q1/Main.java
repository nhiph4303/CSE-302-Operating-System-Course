package Q1;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo danh sách tiến trình
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 0, 5, 3));
        processes.add(new Process("P2", 1, 3, 2));
        processes.add(new Process("P3", 2, 2, 1));
        processes.add(new Process("P4", 3, 4, 4));

        // Tạo đối tượng scheduler và chạy thuật toán
        PreemptivePriorityScheduler scheduler = new PreemptivePriorityScheduler(processes);
        scheduler.schedule();
    }
}