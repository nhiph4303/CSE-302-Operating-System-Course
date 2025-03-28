package Assignment4;

import java.util.ArrayList;

public class Banker {
    private int resourceTypeNumber;
    private int customerNumber;
    private int[] available;
    private int[][] maximum;
    private int[][] allocation;

    public Banker(int[] available, int[][] maximum, int[][] allocation) {
        this.available = available;
        this.maximum = maximum;
        this.allocation = allocation;
        this.resourceTypeNumber = available.length;
        this.customerNumber = maximum.length;
    }

    public ArrayList<Integer> isSafeState() {
        int[] work = available.clone();
        boolean[] finish = new boolean[customerNumber];
        ArrayList<Integer> safeSequence = new ArrayList<>();

        while (safeSequence.size() < customerNumber) {
            boolean progressMade = false;
            for (int i = 0; i < customerNumber; i++) {
                if (!finish[i]) {
                    boolean canFinish = true;
                    for (int j = 0; j < resourceTypeNumber; j++) {
                        if (maximum[i][j] - allocation[i][j] > work[j]) {
                            canFinish = false;
                            break;
                        }
                    }

                    if (canFinish) {
                        for (int j = 0; j < resourceTypeNumber; j++) {
                            work[j] += allocation[i][j];
                        }
                        finish[i] = true;
                        safeSequence.add(i);
                        progressMade = true;
                        break;
                    }
                }
            }

            if (!progressMade) {
                return new ArrayList<>();
            }
        }
        return safeSequence;
    }

    public boolean request(int customerId, int[] request) {
        for (int i = 0; i < resourceTypeNumber; i++) {
            if (request[i] > maximum[customerId][i] - allocation[customerId][i]) {
                return false;
            }
        }

        for (int i = 0; i < resourceTypeNumber; i++) {
            if (request[i] > available[i]) {
                return false;
            }
        }

        for (int i = 0; i < resourceTypeNumber; i++) {
            available[i] -= request[i];
            allocation[customerId][i] += request[i];
        }

        ArrayList<Integer> safeSequence = isSafeState();
        if (safeSequence.isEmpty()) {
            for (int i = 0; i < resourceTypeNumber; i++) {
                available[i] += request[i];
                allocation[customerId][i] -= request[i];
            }
            return false;
        }
        return true;
    }

    public int[] getAvailable() {
        return available;
    }

    public int[][] getMaximum() {
        return maximum;
    }

    public int[][] getAllocation() {
        return allocation;
    }
}
