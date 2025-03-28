package Q3;

public class SubgridValidator implements Runnable {
    private final int[][] board;
    private final int startRow;
    private final int startCol;

    public SubgridValidator(int[][] board, int startRow, int startCol) {
        this.board = board;
        this.startRow = startRow;
        this.startCol = startCol;
    }

    @Override
    public void run() {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = board[startRow + i][startCol + j];
                if (value < 1 || value > 9 || seen[value]) {
                    System.out.println("Subgrid starting at (" + (startRow + 1) + "," + (startCol + 1) + ") is invalid.");
                    return;
                }
                seen[value] = true;
            }
        }
        System.out.println("Subgrid starting at (" + (startRow + 1) + "," + (startCol + 1) + ") is valid.");
    }
}
