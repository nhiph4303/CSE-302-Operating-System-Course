public class Task {
    private String name;
    private int priority;
    private int burst;

    public Task(String name, int priority, int burst) {
        this.name = name;
        this.priority = priority;
        this.burst = burst;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurst() {
        return burst;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }
    
}
