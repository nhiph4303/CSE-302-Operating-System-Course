package Q2;

public class Main {

    public static void main(String[] args) {
        // Total resources available
        int[] totalResources = {12, 9, 12};

        // Define processes with their allocations and maximum resources
        Process[] processes = {
            new Process("P1", new int[]{2, 3, 1}, new int[]{4, 9, 4}),
            new Process("P2", new int[]{1, 2, 3}, new int[]{5, 3, 3}),
            new Process("P3", new int[]{5, 4, 3}, new int[]{6, 4, 3}),
            new Process("P4", new int[]{2, 1, 2}, new int[]{4, 8, 2}),
        };

        // Calculate available resources
        int[] available = BankersAlgorithm.calculateAvailable(totalResources, processes);
        
        // Check if the system is in a safe state
        if (BankersAlgorithm.isSafe(processes, available)) {
            System.out.println("The system is in a safe state.");
        } else {
            System.out.println("The system is NOT in a safe state.");
        }

        // Request for resources from Process T1 (0, 0, 0)
        int[] request = {0, 0, 0};
        int processIndex = 0; // Request from P1 (index 0)
        if (BankersAlgorithm.requestResources(processes, available, request, processIndex)) {
            System.out.println("Request from T1 (0, 0, 0) can be granted.");
        } else {
            System.out.println("Request from T1 (0, 0, 0) cannot be granted.");
        }
    }
}

