package Q2;

public class Process {
    String name;
    int[] allocation;
    int[] max;
    int[] need;

    public Process(String name, int[] allocation, int[] max) {
        this.name = name;
        this.allocation = allocation;
        this.max = max;
        this.need = new int[allocation.length];
        calculateNeed();
    }

    // Method to calculate the need for resources
    private void calculateNeed() {
        for (int i = 0; i < max.length; i++) {
            need[i] = max[i] - allocation[i];
        }
    }

    public int[] getNeed() {
        return need;
    }
}

