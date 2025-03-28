import java.util.*;

public class Scheduler {

    // Method to schedule the processes using priority-based round-robin
    public void schedule(Process[] processes, int quantum) {
        int time = 0;
        Queue<Process> readyQueue = new LinkedList<>();

        // Sort processes by priority (ascending order: 1 is highest priority)
        Arrays.sort(processes, Comparator.comparingInt(p -> p.priority));

        // Add processes that arrive at time 0 to the ready queue
        for (Process p : processes) {
            if (p.arrivalTime == 0) {
                readyQueue.add(p);
            }
        }

        List<String> ganttChart = new ArrayList<>();
        List<String> processOrder = new ArrayList<>();

        // Round-robin scheduling
        while (!readyQueue.isEmpty()) {
            Process currentProcess = readyQueue.poll();
            ganttChart.add(currentProcess.name);
            processOrder.add(currentProcess.name);
            time += Math.min(quantum, currentProcess.remainingTime);
            currentProcess.remainingTime -= quantum;

            if (currentProcess.remainingTime > 0) {
                readyQueue.add(currentProcess); // Put it back at the end of the queue
            } else {
                currentProcess.setCompletionTime(time);
            }

            // Check if other processes arrive while the current process is running
            for (Process p : processes) {
                if (p.arrivalTime <= time && !readyQueue.contains(p) && p.remainingTime > 0) {
                    readyQueue.add(p);
                }
            }
        }

        // Calculate average waiting time and turnaround time
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        for (Process p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        // Print Gantt chart
        System.out.println("Gantt Chart: " + String.join(" | ", ganttChart));
        System.out.println("Process execution order: " + String.join(" -> ", processOrder));
        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / (double) processes.length));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / (double) processes.length));

        // Print waiting time and turnaround time for each process
        for (Process p : processes) {
            System.out.println(p.name + " - Waiting Time: " + p.waitingTime + ", Turnaround Time: " + p.turnaroundTime);
        }
    }
}
