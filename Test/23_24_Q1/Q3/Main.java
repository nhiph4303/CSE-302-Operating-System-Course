package Q3;
public class Main {
    public static void main(String[] args) {
        // Khởi tạo dữ liệu
        int[][] allocation = {
            {0, 0, 1, 2}, // T0
            {1, 0, 0, 0}, // T1
            {1, 3, 5, 4}, // T2
            {0, 6, 3, 2}, // T3
            {0, 0, 1, 4}  // T4
        };

        int[][] max = {
            {0, 0, 1, 2}, // T0
            {1, 7, 5, 0}, // T1
            {2, 3, 5, 6}, // T2
            {0, 6, 5, 2}, // T3
            {0, 6, 5, 6}  // T4
        };

        int[] available = {1, 5, 2, 0}; // A, B, C, D

        // Tạo đối tượng BankerAlgorithm
        BankerAlgorithm banker = new BankerAlgorithm(allocation, max, available);

        // Câu a: Kiểm tra trạng thái an toàn
        System.out.println("Câu a:");
        System.out.println(banker.checkSafeState());

        // Câu b: Xử lý yêu cầu của T1
        System.out.println("\nCâu b:");
        int[] request = {0, 4, 2, 0}; // Yêu cầu của T1
        System.out.println("Yêu cầu của T1: [0, 4, 2, 0]");
        System.out.println(banker.handleRequest(1, request));
    }
}