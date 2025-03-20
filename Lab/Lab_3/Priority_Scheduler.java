import java.util.*;

public class Priority_Scheduler implements Scheduler {
    @Override
    public List<ScheduleInfo> schedule(List<Task> tasks) {
        List<ScheduleInfo> result = new ArrayList<>();
        int currentTime = 0;

        tasks.sort(Comparator.comparingInt(Task::getPriority));

        for (Task task : tasks) {
            int duration = task.getBurst();
            ScheduleInfo info = new ScheduleInfo(currentTime, task, duration);

            result.add(info);
            currentTime += duration;
        }

        return result;
    }
}