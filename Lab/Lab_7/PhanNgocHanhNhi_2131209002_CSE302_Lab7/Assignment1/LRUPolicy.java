package Assignment1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LRUPolicy implements PageReplacementPolicy {
    private int frameNum;
    private ArrayList<Integer> pages;    
    private Map<Integer, Integer> lastUsedMap; 
    private int time; 

    public LRUPolicy(int frameNum) {
        this.frameNum = frameNum;
        this.pages = new ArrayList<>(frameNum);
        this.lastUsedMap = new HashMap<>();
        this.time = 0;
    }

    @Override
    public Result refer(int page) {
        time++;
        int frameIndex = pages.indexOf(page);
        if (frameIndex >= 0) {
            lastUsedMap.put(page, time);
            return new Result(false, frameIndex, -1);
        } else {
            if (pages.size() < frameNum) {
                pages.add(page);
                frameIndex = pages.size() - 1;
                lastUsedMap.put(page, time);
                return new Result(true, frameIndex, -1);
            } else {
                int lruPage = -1;
                int lruTime = Integer.MAX_VALUE;
                int lruFrame = -1;

                for (int i = 0; i < pages.size(); i++) {
                    int p = pages.get(i);
                    int t = lastUsedMap.get(p);
                    if (t < lruTime) {
                        lruTime = t;
                        lruPage = p;
                        lruFrame = i;
                    }
                }
                pages.set(lruFrame, page);
                lastUsedMap.remove(lruPage);
                lastUsedMap.put(page, time);

                return new Result(true, lruFrame, lruPage);
            }
        }
    }
}
