package Q2;

public class BankersAlgorithm {

    // Method to calculate the available resources
    public static int[] calculateAvailable(int[] total, Process[] processes) {
        int[] available = new int[total.length];
        
        // Sum up the allocated resources for each resource type
        for (Process p : processes) {
            for (int i = 0; i < total.length; i++) {
                available[i] += p.allocation[i];
            }
        }

        // Calculate available resources by subtracting allocated from total
        for (int i = 0; i < total.length; i++) {
            available[i] = total[i] - available[i];
        }

        return available;
    }

    // Method to check if the system is in a safe state
    public static boolean isSafe(Process[] processes, int[] available) {
        int numProcesses = processes.length;
        boolean[] finished = new boolean[numProcesses];
        int[] work = available.clone();
        
        while (true) {
            boolean progressMade = false;
            for (int i = 0; i < numProcesses; i++) {
                if (!finished[i]) {
                    Process p = processes[i];
                    boolean canComplete = true;
                    // Check if this process can finish with current available resources
                    for (int j = 0; j < p.getNeed().length; j++) {
                        if (p.getNeed()[j] > work[j]) {
                            canComplete = false;
                            break;
                        }
                    }
                    if (canComplete) {
                        // Add allocated resources to work
                        for (int j = 0; j < p.allocation.length; j++) {
                            work[j] += p.allocation[j];
                        }
                        finished[i] = true;
                        progressMade = true;
                    }
                }
            }

            if (!progressMade) {
                // If no progress is made, then the system is in an unsafe state
                for (boolean f : finished) {
                    if (!f) return false;
                }
                return true; // All processes finished, system is safe
            }
        }
    }

    // Method to request resources and check if the request can be granted
    public static boolean requestResources(Process[] processes, int[] available, int[] request, int processIndex) {
        int numResources = available.length;
        Process p = processes[processIndex];

        // Check if request is less than the need for the process
        for (int i = 0; i < numResources; i++) {
            if (request[i] > p.getNeed()[i]) {
                return false; // Request exceeds the need of the process
            }
        }

        // Check if the request is less than or equal to the available resources
        for (int i = 0; i < numResources; i++) {
            if (request[i] > available[i]) {
                return false; // Not enough resources available
            }
        }

        // Temporarily allocate the resources
        for (int i = 0; i < numResources; i++) {
            available[i] -= request[i];
            p.allocation[i] += request[i];
        }

        // Check if the system remains safe after the allocation
        if (isSafe(processes, available)) {
            return true;
        } else {
            // If the system is unsafe, undo the allocation
            for (int i = 0; i < numResources; i++) {
                available[i] += request[i];
                p.allocation[i] -= request[i];
            }
            return false;
        }
    }
}

