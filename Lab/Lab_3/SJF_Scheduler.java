import java.util.*;

public class SJF_Scheduler implements Scheduler {
    @Override
    public List<ScheduleInfo> schedule(List<Task> tasks) {
        List<ScheduleInfo> result = new ArrayList<>();

        // Sắp xếp danh sách task theo burst tăng dần
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return Integer.compare(t1.getBurst(), t2.getBurst());
            }
        });

        int currentTime = 0;
        for (Task task : tasks) {
            int duration = task.getBurst();
            ScheduleInfo info = new ScheduleInfo(currentTime, task, duration);
            result.add(info);
            currentTime += task.getBurst();
        }

        return result;
    }
}
