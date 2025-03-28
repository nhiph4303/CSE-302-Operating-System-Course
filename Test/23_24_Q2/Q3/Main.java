package Q3;

public class Main {

    public static void main(String[] args) {
        // Page reference string
        int[] pages = {2, 6, 9, 2, 4, 2, 1, 7, 3, 0, 5, 2, 1, 2, 9};
        int frameCount = 3;
        
        // Create objects for each algorithm
        PageReplacement fifo = new FIFO();
        PageReplacement lru = new LRU();
        PageReplacement optimal = new Optimal();
        
        // Calculate page faults for each algorithm
        int fifoPageFaults = fifo.calculatePageFaults(pages, frameCount);
        int lruPageFaults = lru.calculatePageFaults(pages, frameCount);
        int optimalPageFaults = optimal.calculatePageFaults(pages, frameCount);
        
        // Output results
        System.out.println("FIFO page faults: " + fifoPageFaults);
        System.out.println("LRU page faults: " + lruPageFaults);
        System.out.println("Optimal page faults: " + optimalPageFaults);
    }
}

