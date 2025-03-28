package Q3;

public class BankerAlgorithm {
    private int[][] allocation; // Ma trận Allocation
    private int[][] max;        // Ma trận Max
    private int[][] need;       // Ma trận Need
    private int[] available;    // Vector Available
    private int numThreads;     // Số luồng
    private int numResources;   // Số loại tài nguyên

    public BankerAlgorithm(int[][] allocation, int[][] max, int[] available) {
        this.allocation = allocation;
        this.max = max;
        this.available = available;
        this.numThreads = allocation.length;
        this.numResources = available.length;

        // Tính Need Matrix
        this.need = new int[numThreads][numResources];
        for (int i = 0; i < numThreads; i++) {
            for (int j = 0; j < numResources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    // Kiểm tra trạng thái an toàn và trả về thứ tự thực thi an toàn
    public String checkSafeState() {
        int[] work = available.clone();
        boolean[] finish = new boolean[numThreads];
        StringBuilder safeSequence = new StringBuilder();

        int count = 0;
        while (count < numThreads) {
            boolean found = false;
            for (int i = 0; i < numThreads; i++) {
                if (!finish[i] && canAllocate(need[i], work)) {
                    // Cấp tài nguyên cho luồng i
                    for (int j = 0; j < numResources; j++) {
                        work[j] += allocation[i][j];
                    }
                    finish[i] = true;
                    safeSequence.append("T").append(i).append(", ");
                    count++;
                    found = true;
                }
            }
            if (!found) {
                return "Hệ thống không ở trạng thái an toàn.";
            }
        }
        return "Hệ thống ở trạng thái an toàn. Thứ tự thực thi an toàn: <" + safeSequence.substring(0, safeSequence.length() - 2) + ">";
    }

    // Kiểm tra xem có thể cấp tài nguyên cho một luồng không
    private boolean canAllocate(int[] need, int[] work) {
        for (int i = 0; i < numResources; i++) {
            if (need[i] > work[i]) {
                return false;
            }
        }
        return true;
    }

    // Xử lý yêu cầu tài nguyên
    public String handleRequest(int threadId, int[] request) {
        // Bước 1: Kiểm tra yêu cầu có hợp lệ không
        for (int i = 0; i < numResources; i++) {
            if (request[i] > need[threadId][i]) {
                return "Yêu cầu không hợp lệ: Vượt quá nhu cầu tối đa của T" + threadId;
            }
            if (request[i] > available[i]) {
                return "Yêu cầu không thể cấp ngay lập tức: Không đủ tài nguyên hiện có.";
            }
        }

        // Bước 2: Giả sử cấp tài nguyên
        int[] tempAvailable = available.clone();
        int[][] tempAllocation = new int[numThreads][numResources];
        int[][] tempNeed = new int[numThreads][numResources];

        // Sao chép ma trận Allocation và Need
        for (int i = 0; i < numThreads; i++) {
            tempAllocation[i] = allocation[i].clone();
            tempNeed[i] = need[i].clone();
        }

        // Cập nhật trạng thái
        for (int i = 0; i < numResources; i++) {
            tempAvailable[i] -= request[i];
            tempAllocation[threadId][i] += request[i];
            tempNeed[threadId][i] -= request[i];
        }

        // Bước 3: Kiểm tra trạng thái an toàn với trạng thái mới
        int[] work = tempAvailable.clone();
        boolean[] finish = new boolean[numThreads];
        int count = 0;

        while (count < numThreads) {
            boolean found = false;
            for (int i = 0; i < numThreads; i++) {
                if (!finish[i] && canAllocate(tempNeed[i], work)) {
                    for (int j = 0; j < numResources; j++) {
                        work[j] += tempAllocation[i][j];
                    }
                    finish[i] = true;
                    count++;
                    found = true;
                }
            }
            if (!found) {
                return "Yêu cầu không thể cấp ngay lập tức: Hệ thống sẽ không ở trạng thái an toàn.";
            }
        }

        // Cập nhật trạng thái thực sự nếu yêu cầu được chấp nhận
        available = tempAvailable;
        allocation = tempAllocation;
        need = tempNeed;
        return "Yêu cầu có thể được cấp ngay lập tức. Hệ thống vẫn ở trạng thái an toàn.";
    }
}