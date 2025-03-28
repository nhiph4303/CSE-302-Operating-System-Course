package Q3;

public class ColumnValidator implements Runnable {
    private final int[][] board;
    private final int col;

    public ColumnValidator(int[][] board, int col) {
        this.board = board;
        this.col = col;
    }

    @Override
    public void run() {
        boolean[] seen = new boolean[10];
        for (int row = 0; row < 9; row++) {
            int value = board[row][col];
            if (value < 1 || value > 9 || seen[value]) {
                System.out.println("Column " + (col + 1) + " is invalid.");
                return;
            }
            seen[value] = true;
        }
        System.out.println("Column " + (col + 1) + " is valid.");
    }
}
