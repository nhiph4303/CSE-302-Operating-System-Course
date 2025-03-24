package Assignment1;
import java.util.ArrayList;

public class OPTPolicy implements PageReplacementPolicy {
    private int frameNum;
    private ArrayList<Integer> pages; 
    private int[] refString;         
    private int currentIndex;       

    public OPTPolicy(int frameNum, int[] refString) {
        this.frameNum = frameNum;
        this.refString = refString;
        this.pages = new ArrayList<>(frameNum);
        this.currentIndex = 0;
    }

    @Override
    public Result refer(int page) {
        int frameIndex = pages.indexOf(page);
        if (frameIndex >= 0) {
            currentIndex++;
            return new Result(false, frameIndex, -1);
        } else {
            if (pages.size() < frameNum) {
                pages.add(page);
                int idx = pages.size() - 1;
                currentIndex++;
                return new Result(true, idx, -1);
            } else {
                int pageToReplace = -1;
                int frameToReplace = -1;
                int farthestIndex = -1;

                for (int i = 0; i < pages.size(); i++) {
                    int p = pages.get(i);
                    int nextUse = findNextUse(p, currentIndex, refString);
                    if (nextUse == -1) {
                        pageToReplace = p;
                        frameToReplace = i;
                        break; 
                    } else if (nextUse > farthestIndex) {
                        farthestIndex = nextUse;
                        pageToReplace = p;
                        frameToReplace = i;
                    }
                }
                pages.set(frameToReplace, page);
                currentIndex++;
                return new Result(true, frameToReplace, pageToReplace);
            }
        }
    }

    private int findNextUse(int p, int start, int[] refString) {
        for (int i = start; i < refString.length; i++) {
            if (refString[i] == p) {
                return i;
            }
        }
        return -1;
    }
}
