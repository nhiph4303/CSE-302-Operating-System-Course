public class ScheduleInfo {
    private int startTime;  
    private Task task;      
    private int duration;  

    // Constructor
    public ScheduleInfo(int startTime, Task task, int duration) {
        this.startTime = startTime;
        this.task = task;
        this.duration = duration;
    }

    public int getStartTime() {
        return startTime;
    }

    public Task getTask() {
        return task;
    }

    public int getDuration() {
        return duration;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%d : %s - Pri: %d - Burst: %d - Duration: %d",
                startTime, task.getName(), task.getPriority(), task.getBurst(), duration);
    }
}
