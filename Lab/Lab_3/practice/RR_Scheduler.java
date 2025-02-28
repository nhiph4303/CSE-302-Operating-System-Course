import java.util.*;

public class RR_Scheduler implements Scheduler {
    private final int timeQuantum;

    public RR_Scheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    @Override
    public List<ScheduleInfo> schedule(List<Task> tasks) {
        List<ScheduleInfo> result = new ArrayList<>();
        Queue<Task> queue = new LinkedList<>(tasks); 

        int currentTime = 0;

        while (!queue.isEmpty()) {
            Task task = queue.poll(); 
            int burstTime = task.getBurst();
            
            int executionTime = Math.min(burstTime, timeQuantum);
            result.add(new ScheduleInfo(currentTime, task, executionTime));
            currentTime += executionTime; 

            if (burstTime > timeQuantum) {
                queue.add(new Task(task.getName(), task.getPriority(), burstTime - timeQuantum));
            }
        }
        return result;
    }
}
