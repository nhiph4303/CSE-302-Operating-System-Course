import java.util.List;

public interface Scheduler {
    List<ScheduleInfo> schedule(List<Task> tasks);
}
