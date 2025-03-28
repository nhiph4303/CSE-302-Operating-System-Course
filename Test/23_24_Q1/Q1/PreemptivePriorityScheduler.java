package Q1;
import java.util.*;

public class PreemptivePriorityScheduler {
    private List<Process> processes;
    private List<String> ganttChart;

    public PreemptivePriorityScheduler(List<Process> processes) {
        this.processes = processes;
        this.ganttChart = new ArrayList<>();
    }

    public void schedule() {
        // Sắp xếp tiến trình theo thời gian đến
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        int currentTime = 0;
        int completed = 0;

        while (completed < processes.size()) {
            // Tìm tiến trình có ưu tiên cao nhất (số nhỏ hơn) tại thời điểm currentTime
            Process selected = null;
            int highestPriority = Integer.MAX_VALUE;

            for (Process p : processes) {
                if (p.getArrivalTime() <= currentTime && p.getRemainingTime() > 0) {
                    if (p.getPriority() < highestPriority) {
                        highestPriority = p.getPriority();
                        selected = p;
                    }
                }
            }

            if (selected == null) {
                ganttChart.add("Idle");
                currentTime++;
                continue;
            }

            // Thực thi tiến trình được chọn
            ganttChart.add(selected.getName());
            selected.setRemainingTime(selected.getRemainingTime() - 1);
            currentTime++;

            // Nếu tiến trình hoàn thành
            if (selected.getRemainingTime() == 0) {
                completed++;
                selected.setCompletionTime(currentTime);
                selected.setTurnaroundTime(selected.getCompletionTime() - selected.getArrivalTime());
                selected.setWaitingTime(selected.getTurnaroundTime() - selected.getBurstTime());
            }
        }

        // In kết quả
        printGanttChart();
        printResults();
    }

    private void printGanttChart() {
        System.out.println("Biểu đồ Gantt:");
        for (int i = 0; i < ganttChart.size(); i++) {
            System.out.print(i + " - " + ganttChart.get(i) + " | ");
        }
        System.out.println(ganttChart.size());
    }

    private void printResults() {
        double avgWaitingTime = 0;
        double avgTurnaroundTime = 0;

        System.out.println("\nTiến trình | Thời gian chờ | Thời gian quay vòng");
        for (Process p : processes) {
            System.out.printf("%s | %d | %d\n", p.getName(), p.getWaitingTime(), p.getTurnaroundTime());
            avgWaitingTime += p.getWaitingTime();
            avgTurnaroundTime += p.getTurnaroundTime();
        }

        avgWaitingTime /= processes.size();
        avgTurnaroundTime /= processes.size();

        System.out.println("\nThời gian chờ trung bình: " + avgWaitingTime);
        System.out.println("Thời gian quay vòng trung bình: " + avgTurnaroundTime);
    }
}