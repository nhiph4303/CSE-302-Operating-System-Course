package Q3_1;

import java.util.*;

public class SudokuValidator {
    static int[][] board = {
        {6, 2, 4, 5, 3, 9, 0, 1, 8},
        {5, 1, 9, 7, 2, 8, 6, 3, 4},
        {8, 3, 7, 6, 1, 4, 2, 0, 5},
        {1, 4, 3, 8, 6, 5, 7, 2, 9},
        {9, 5, 8, 2, 4, 7, 3, 6, 1},
        {7, 6, 2, 3, 9, 1, 4, 5, 8},
        {3, 7, 1, 9, 5, 6, 8, 4, 2},
        {4, 9, 6, 1, 8, 2, 5, 7, 3},
        {2, 8, 5, 4, 7, 3, 9, 1, 6}
    };
    static volatile boolean isValid = true;

    static boolean checkSet(int[] set) {
        Set<Integer> seen = new HashSet<>();
        for (int num : set) {
            if (num == 0) continue;
            if (num < 1 || num > 9 || !seen.add(num)) return false;
        }
        return true;
    }

    static class RowChecker implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 9; i++) {
                if (!checkSet(board[i])) {
                    isValid = false;
                    System.out.println("Hàng " + (i + 1) + " không hợp lệ");
                    return;
                }
            }
            System.out.println("Tất cả hàng hợp lệ");
        }
    }

    static class ColumnChecker implements Runnable {
        @Override
        public void run() {
            for (int j = 0; j < 9; j++) {
                int[] column = new int[9];
                for (int i = 0; i < 9; i++) column[i] = board[i][j];
                if (!checkSet(column)) {
                    isValid = false;
                    System.out.println("Cột " + (j + 1) + " không hợp lệ");
                    return;
                }
            }
            System.out.println("Tất cả cột hợp lệ");
        }
    }

    static class SubgridChecker implements Runnable {
        private final int startRow, startCol;

        SubgridChecker(int startRow, int startCol) {
            this.startRow = startRow;
            this.startCol = startCol;
        }

        @Override
        public void run() {
            int[] subgrid = new int[9];
            int idx = 0;
            for (int i = startRow; i < startRow + 3; i++) {
                for (int j = startCol; j < startCol + 3; j++) {
                    subgrid[idx++] = board[i][j];
                }
            }
            if (!checkSet(subgrid)) {
                isValid = false;
                System.out.println("Ô 3x3 tại (" + startRow + "," + startCol + ") không hợp lệ");
            } else {
                System.out.println("Ô 3x3 tại (" + startRow + "," + startCol + ") hợp lệ");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread rowThread = new Thread(new RowChecker());
        Thread colThread = new Thread(new ColumnChecker());
        Thread[] subgridThreads = new Thread[9];
        int idx = 0;
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                subgridThreads[idx++] = new Thread(new SubgridChecker(i, j));
            }
        }

        rowThread.start();
        colThread.start();
        for (Thread t : subgridThreads) t.start();

        rowThread.join();
        colThread.join();
        for (Thread t : subgridThreads) t.join();

        System.out.println("\nKết luận: Bảng Sudoku " + (isValid ? "hợp lệ" : "không hợp lệ"));
    }
}