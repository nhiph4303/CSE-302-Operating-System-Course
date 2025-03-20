
import java.util.LinkedList;

public class FIFOPolicy implement ReplacementPolicy {
    private int entryNum;
    private int[] values;
    private LinkedList<Integer> positionQueue = new LinkedList<>();

    public FIFOPolicy(int entryNum) {
        this.entryNum = entryNum;
        this.values = new int[entryNum];
        for (int i =0; i<this.entryNum; i++){
            this.values[i] = -1;
        }
        for(int i = 0; i < entryNum; i++){
            positionQueue.addLast(i);
        }
    }

    @Override
    public ReplacementPolicy.Result refer(int value){
    }

    
}
