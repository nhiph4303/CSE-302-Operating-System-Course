package Q3;

public class RowValidator implements Runnable {
    private final int[][] board;
    private final int row;

    public RowValidator(int[][] board, int row) {
        this.board = board;
        this.row = row;
    }

    @Override
    public void run() {
        boolean[] seen = new boolean[10];  // Sử dụng boolean để kiểm tra các số từ 1 đến 9
        for (int col = 0; col < 9; col++) {
            int value = board[row][col];
            if (value < 1 || value > 9 || seen[value]) {
                System.out.println("Row " + (row + 1) + " is invalid.");
                return;
            }
            seen[value] = true;
        }
        System.out.println("Row " + (row + 1) + " is valid.");
    }
}
