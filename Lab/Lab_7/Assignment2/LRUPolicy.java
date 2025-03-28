import java.util.LinkedHashMap;
import java.util.Map;

public class LRUPolicy implements ReplacementPolicy {
    private final LinkedHashMap<Integer, Integer> lruMap; // key: position, value: page
    private final int entryNum;

    public LRUPolicy(int entryNum) {
        this.entryNum = entryNum;
        this.lruMap = new LinkedHashMap<>(entryNum, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > entryNum;
            }
        };
    }

    @Override
    public ReplacementPolicy.Result refer(int value) {
        // Check if the value exists in any position
        boolean found = false;
        int foundPosition = -1;
        
        for (Map.Entry<Integer, Integer> entry : lruMap.entrySet()) {
            if (entry.getValue() == value) {
                found = true;
                foundPosition = entry.getKey();
                break;
            }
        }

        if (found) {
            // Access the entry to mark it as recently used (LinkedHashMap will move it to end)
            lruMap.get(foundPosition);
            return new ReplacementPolicy.Result(true, value, foundPosition, -1);
        } else {
            // Need to find a position to put the new value
            int position;
            int replacedValue = -1;
            
            if (lruMap.size() >= entryNum) {
                // Remove the least recently used entry (first in iteration order)
                Map.Entry<Integer, Integer> eldest = lruMap.entrySet().iterator().next();
                position = eldest.getKey();
                replacedValue = eldest.getValue();
                lruMap.remove(position);
            } else {
                // Find an empty position
                position = findEmptyPosition();
            }
            
            lruMap.put(position, value);
            return new ReplacementPolicy.Result(false, value, position, replacedValue);
        }
    }

    @Override
    public void remove(int value) {
        Integer positionToRemove = null;
        for (Map.Entry<Integer, Integer> entry : lruMap.entrySet()) {
            if (entry.getValue() == value) {
                positionToRemove = entry.getKey();
                break;
            }
        }
        if (positionToRemove != null) {
            lruMap.remove(positionToRemove);
        }
    }

    private int findEmptyPosition() {
        for (int i = 0; i < entryNum; i++) {
            if (!lruMap.containsKey(i)) {
                return i;
            }
        }
        return -1; // Shouldn't happen if size < entryNum
    }
}