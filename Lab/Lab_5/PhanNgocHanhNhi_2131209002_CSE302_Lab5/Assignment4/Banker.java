package Assignment4;

public class Banker {
    private int resourceTypeNum;  
    private int customerNum;      
    private int[] available;      
    private int[][] maximum;       
    private int[][] allocation;  

    public Banker(int[] avail, int[][] max, int[][] alloc) throws Exception {
        this.available = avail;
        this.maximum = max;
        this.allocation = alloc;
        this.resourceTypeNum = avail.length;
        this.customerNum = alloc.length;
    }

    private int[][] getNeed() {
        int[][] need = new int[customerNum][resourceTypeNum];
        for (int i = 0; i < customerNum; i++) {
            for (int j = 0; j < resourceTypeNum; j++) {
                need[i][j] = maximum[i][j] - allocation[i][j];
            }
        }
        return need;
    }

    public boolean isSafeState() {
        int[][] need = getNeed();
        boolean[] finish = new boolean[customerNum];
        int[] work = new int[resourceTypeNum];
        System.arraycopy(available, 0, work, 0, resourceTypeNum);

        boolean progressMade = true;
        while (progressMade) {
            progressMade = false;
            for (int i = 0; i < customerNum; i++) {
                if (!finish[i]) {
                    boolean canFinish = true;
                    for (int j = 0; j < resourceTypeNum; j++) {
                        if (need[i][j] > work[j]) {
                            canFinish = false;
                            break;
                        }
                    }
                    if (canFinish) {
                        for (int j = 0; j < resourceTypeNum; j++) {
                            work[j] += allocation[i][j];
                        }
                        finish[i] = true;
                        progressMade = true;
                    }
                }
            }
        }

        for (boolean f : finish) {
            if (!f) {
                return false;  
            }
        }
        return true;  
    }

    public boolean request(int custId, int[] request) {
        int[][] need = getNeed();
        for (int i = 0; i < resourceTypeNum; i++) {
            if (request[i] > need[custId][i]) {
                return false; 
            }
        }

        for (int i = 0; i < resourceTypeNum; i++) {
            if (request[i] > available[i]) {
                return false; 
            }
        }

        for (int i = 0; i < resourceTypeNum; i++) {
            available[i] -= request[i];
            allocation[custId][i] += request[i];
            need[custId][i] -= request[i];
        }

        if (isSafeState()) {
            return true; 
        } else {
            for (int i = 0; i < resourceTypeNum; i++) {
                available[i] += request[i];
                allocation[custId][i] -= request[i];
                need[custId][i] += request[i];
            }
            return false;
        }
    }
}

