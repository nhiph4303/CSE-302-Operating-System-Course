import java.io.*;
import java.util.*;

public class Main {
    public static final String TASK_FILE = "schedule.txt";

    public static void main(String[] args) {
        List<Task> queue = new ArrayList<>();
        BufferedReader rd = null; // Khai báo rd bên ngoài khối try

        try {
            rd = new BufferedReader(new FileReader(Main.TASK_FILE));
            String line;
            while ((line = rd.readLine()) != null) { // Đọc từng dòng
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

        Scheduler fcfs_scheduler = new FCFS_Scheduler();
        List<ScheduleInfo> result1 = fcfs_scheduler.schedule(queue);

        for (ScheduleInfo info : result1) {
            System.out.println(info);
        }

        Scheduler sjf_scheduler = new SJF_Scheduler();
        List<ScheduleInfo> result2 = sjf_scheduler.schedule(queue);

        for (ScheduleInfo info : result2) {
            System.out.println(info);
        }
    }
}
