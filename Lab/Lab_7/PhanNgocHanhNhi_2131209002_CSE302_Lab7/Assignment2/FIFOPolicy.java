package Assignment2;

import java.util.LinkedList;

public class FIFOPolicy implements ReplacementPolicy {
    private int entryNum;
    private int[] values;
    private LinkedList<Integer> positionQueue = new LinkedList<>();

    public FIFOPolicy(int entryNum) {
        this.entryNum = entryNum;
        this.values = new int[entryNum];
        for (int i = 0; i < values.length; i++) {
            this.values[i] = -1;
        }
        for (int i = 0; i < values.length; i++) {
            this.positionQueue.addLast(i);
        }
    }

    @Override
    public Result refer(int value) {
        for (int i = 0; i < values.length; i++) {
            if (this.values[i] == value) {
                return new Result(true, value, i, -1);
            }
        }
        int replacedPosition = this.positionQueue.removeFirst();
        int replacedValue = this.values[replacedPosition];
        this.values[replacedPosition] = value;
        this.positionQueue.addLast(replacedPosition);

        return new Result(false, value, replacedPosition, replacedValue);
    }

    @Override
    public void remove(int value) {
        for (int i = 0; i < values.length; i++) {
            if (this.values[i] == value) {
                this.values[i] = -1;
                this.positionQueue.remove((Integer)i);
                this.positionQueue.addFirst(i);
                return;
            }
        }
    }
}
