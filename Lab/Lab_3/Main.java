import java.io.*;
import java.util.*;

public class Main {
    public static final String TASK_FILE = "schedule.txt";

    public static void main(String[] args) {
        List<Task> queue = new ArrayList<>();
        BufferedReader rd = null;

        try {
            rd = new BufferedReader(new FileReader(Main.TASK_FILE));
            String line;
            while ((line = rd.readLine()) != null) {
                String[] components = line.split(",");
                if (components.length != 3) {
                    System.out.println("Invalid format: " + line);
                    return;
                }
                String name = components[0].trim();
                int priority = Integer.parseInt(components[1].trim());
                int burst = Integer.parseInt(components[2].trim());

                Task task = new Task(name, priority, burst);
                queue.add(task);
            }
        } catch (FileNotFoundException e1) {
            System.err.println("File not found: " + TASK_FILE);
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        runScheduler(new FCFS_Scheduler(), queue, "First Come First Serve (FCFS)");
        runScheduler(new SJF_Scheduler(), queue, "Shortest Job First (SJF)");
        runScheduler(new Priority_Scheduler(), queue, "Priority Scheduling");
        runScheduler(new RR_Scheduler(10), queue, "Round-robin (RR) scheduling");
        runScheduler(new PriorityRR_Scheduler(10), queue, "Priority with round-robin scheduling");
    }

    private static void runScheduler(Scheduler scheduler, List<Task> queue, String algorithmName) {
        System.out.println("\nAlgorithm: " + algorithmName);
        List<ScheduleInfo> results = scheduler.schedule(new ArrayList<>(queue));

        for (ScheduleInfo info : results) {
            System.out.println(info);
        }
    }

}
