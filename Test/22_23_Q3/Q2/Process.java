package Q2;

public class Process {
    int id;
    int[] allocation = new int[4]; // A, B, C, D
    int[] max = new int[4];
    int[] need = new int[4];

    public Process(int id, int[] allocation, int[] max) {
        this.id = id;
        this.allocation = allocation;
        this.max = max;
        for (int i = 0; i < 4; i++) {
            this.need[i] = max[i] - allocation[i]; // tính toán need
        }
    }

    // Getter và setter cho các thuộc tính của Process
    public int getId() {
        return id;
    }

    public int[] getAllocation() {
        return allocation;
    }

    public int[] getMax() {
        return max;
    }

    public int[] getNeed() {
        return need;
    }
}

