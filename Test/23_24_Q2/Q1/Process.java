public class Process {
    String name;
    int burstTime;
    int priority;
    int remainingTime;
    int arrivalTime;
    int startTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public Process(String name, int burstTime, int priority, int arrivalTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.remainingTime = burstTime;
    }

    // Method to calculate completion time, turnaround time, and waiting time
    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
        this.turnaroundTime = completionTime - arrivalTime;
        this.waitingTime = turnaroundTime - burstTime;
    }
}
