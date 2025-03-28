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
            this.positionQueue.addFirst(i);
        }
    }

    @Override
    public ReplacementPolicy.Result refer(int value) {
        Result result;
        for (int i = 0; i < values.length; i++) {
            if (this.values[i] == value) { // found
                result = new Result(true, value, i, -1);
                return result;
            }
        }
        int replacedPosition = this.positionQueue.removeFirst();
        int replacedValue = this.values[replacedPosition];
        this.values[replacedPosition] = value;

        this.positionQueue.addLast(replacedPosition);

        result = new Result(false, value, replacedPosition, replacedValue);
        return result;
    }

    @Override
    public void remove(int value) {
        for (int i = 0; i < values.length; i++) {
            if (this.values[i] == value) { // found
                this.values[i] = -1;
                this.positionQueue.remove(i);
                this.positionQueue.addFirst(i);
                return;
            }
        }
    }

}
