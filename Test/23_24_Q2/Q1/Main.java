public class Main {

    public static void main(String[] args) {
        // Define the processes
        Process[] processes = {
            new Process("P1", 20, 4, 0),
            new Process("P2", 25, 3, 0),
            new Process("P3", 25, 1, 20),
            new Process("P4", 15, 5, 25),
            new Process("P5", 20, 5, 45)
        };

        // Set time quantum
        int quantum = 10;

        // Create Scheduler and schedule the processes
        Scheduler scheduler = new Scheduler();
        scheduler.schedule(processes, quantum);
    }
}
