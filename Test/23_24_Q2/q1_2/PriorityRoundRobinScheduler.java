import java.util.*;

class Process {
    String name;
    int burst, priority, remaining, completion, tat, wt;

    Process(String name, int burst, int priority) {
        this.name = name;
        this.burst = burst;
        this.priority = priority;
        this.remaining = burst;
    }
}

public class PriorityRoundRobinScheduler {
    static final int TIME_QUANTUM = 10;

    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 20, 4));
        processes.add(new Process("P2", 25, 3));
        processes.add(new Process("P3", 25, 1));
        processes.add(new Process("P4", 15, 5));
        processes.add(new Process("P5", 20, 5));

        schedule(processes);
    }

    static void schedule(List<Process> processes) {
        Map<Integer, Queue<Process>> queues = new TreeMap<>();
        for (Process p : processes) {
            queues.computeIfAbsent(p.priority, k -> new LinkedList<>()).add(p);
        }

        List<String> gantt = new ArrayList<>();
        int time = 0, completed = 0, quantum = 0;
        Process current = null;

        while (completed < processes.size()) {
            if (current == null || quantum >= TIME_QUANTUM) {
                current = null;
                quantum = 0;
                for (Queue<Process> queue : queues.values()) {
                    if (!queue.isEmpty()) {
                        current = queue.poll();
                        break;
                    }
                }
            }

            if (current == null) {
                gantt.add("Idle");
                time++;
                continue;
            }

            gantt.add(current.name);
            current.remaining--;
            quantum++;
            time++;

            if (current.remaining == 0) {
                completed++;
                current.completion = time;
                current.tat = current.completion;
                current.wt = current.tat - current.burst;
                current = null;
                quantum = 0;
            } else if (quantum >= TIME_QUANTUM) {
                queues.get(current.priority).add(current);
                current = null;
                quantum = 0;
            }
        }

        // In biểu đồ Gantt
        System.out.println("Biểu đồ Gantt:");
        int start = 0;
        String prev = gantt.get(0);
        for (int i = 1; i < gantt.size(); i++) {
            if (!gantt.get(i).equals(prev)) {
                System.out.print(start + "-" + i + ": " + prev + " | ");
                start = i;
                prev = gantt.get(i);
            }
        }
        System.out.println(start + "-" + gantt.size() + ": " + prev);

        // Tính thời gian trung bình
        double avgWt = 0, avgTat = 0;
        System.out.println("\nProcess | TAT | WT");
        for (Process p : processes) {
            System.out.printf("%s | %d | %d\n", p.name, p.tat, p.wt);
            avgWt += p.wt;
            avgTat += p.tat;
        }
        avgWt /= processes.size();
        avgTat /= processes.size();
        System.out.println("\nThời gian chờ trung bình: " + avgWt);
        System.out.println("Thời gian quay vòng trung bình: " + avgTat);
    }
}