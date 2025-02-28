import java.util.*;

public class PriorityRR_Scheduler implements Scheduler {
    private final int timeQuantum;

    public PriorityRR_Scheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    @Override
    public List<ScheduleInfo> schedule(List<Task> tasks) {
        List<ScheduleInfo> result = new ArrayList<>();
        Map<Integer, Queue<Task>> priorityGroups = new TreeMap<>(); 
        for (Task task : tasks) {
            priorityGroups.putIfAbsent(task.getPriority(), new LinkedList<>());
            priorityGroups.get(task.getPriority()).add(task);
        }

        int currentTime = 0;

        for (Queue<Task> queue : priorityGroups.values()) {
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
        }
        return result;
    }
}
