import java.util.*;

public class PreemptivePriorityScheduling {
    static class Process {
        int id, arrivalTime, burstTime, priority, remainingTime, completionTime, turnaroundTime, waitingTime;

        public Process(int id, int arrivalTime, int burstTime, int priority) {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.priority = priority;
            this.remainingTime = burstTime;
        }
    }

    public static void main(String[] args) {
        // Khởi tạo các tiến trình
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 0, 11, 2));
        processes.add(new Process(2, 5, 28, 0));
        processes.add(new Process(3, 12, 2, 3));
        processes.add(new Process(4, 2, 10, 1));
        processes.add(new Process(5, 9, 16, 4));

        // Sắp xếp tiến trình theo thời gian đến
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int time = 0;
        int completed = 0;
        int n = processes.size();

        // Hàng đợi các tiến trình sẵn sàng theo ưu tiên
        Queue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt((Process p) -> p.priority).thenComparingInt(p -> p.arrivalTime));
        Process currentProcess = null;

        // Lập lịch tiến trình
        while (completed < n) {
            // Thêm các tiến trình đã đến vào hàng đợi sẵn sàng
            for (Process p : processes) {
                if (p.arrivalTime <= time && p.remainingTime > 0 && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }

            if (!readyQueue.isEmpty()) {
                currentProcess = readyQueue.poll();
                int startTime = time;

                // Chạy tiến trình
                int runTime = Math.min(currentProcess.remainingTime, 10); // Time quantum = 10
                currentProcess.remainingTime -= runTime;
                time += runTime;

                // Nếu tiến trình hoàn thành
                if (currentProcess.remainingTime == 0) {
                    currentProcess.completionTime = time;
                    currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                    currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
                    completed++;
                } else {
                    readyQueue.add(currentProcess); // Tiến trình chưa hoàn thành sẽ quay lại hàng đợi
                }
            } else {
                time++; // Nếu không có tiến trình nào có sẵn, tăng thời gian lên 1ms
            }
        }

        // In kết quả
        int totalTurnaroundTime = 0, totalWaitingTime = 0;
        System.out.println("Process\tTurnaround Time\tWaiting Time");
        for (Process p : processes) {
            System.out.println("P" + p.id + "\t\t" + p.turnaroundTime + "\t\t\t" + p.waitingTime);
            totalTurnaroundTime += p.turnaroundTime;
            totalWaitingTime += p.waitingTime;
        }

        System.out.println("\nAverage Turnaround Time: " + (totalTurnaroundTime / n));
        System.out.println("Average Waiting Time: " + (totalWaitingTime / n));
    }
}
