package Q3;

import java.util.concurrent.*;

public class SudokuValidator {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int[][] board = {
        {6, 2, 4, 5, 3, 9, 1, 8, 7},
        {5, 1, 9, 7, 2, 8, 6, 3, 4},
        {8, 3, 7, 6, 1, 4, 2, 9, 5},
        {1, 4, 3, 8, 6, 5, 7, 2, 9},
        {9, 5, 8, 2, 4, 7, 3, 6, 1},
        {7, 6, 2, 3, 9, 1, 4, 5, 8},
        {3, 7, 1, 9, 5, 6, 8, 4, 2},
        {4, 9, 6, 1, 8, 2, 5, 7, 3},
        {2, 8, 5, 4, 7, 3, 9, 1, 6}
    };

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(13);  // 1 for columns, 1 for rows, 9 for subgrids

        // Kiểm tra cột
        for (int col = 0; col < SIZE; col++) {
            executor.submit(new ColumnValidator(board, col));
        }

        // Kiểm tra hàng
        for (int row = 0; row < SIZE; row++) {
            executor.submit(new RowValidator(board, row));
        }

        // Kiểm tra các ô con 3x3 theo đúng thứ tự
        for (int row = 0; row < SIZE; row += SUBGRID_SIZE) {
            for (int col = 0; col < SIZE; col += SUBGRID_SIZE) {
                executor.submit(new SubgridValidator(board, row, col));
            }
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES); // Đợi các luồng hoàn thành
    }
}
