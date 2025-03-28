package Q3;
import java.util.*;

public class Optimal extends PageReplacement {

    @Override
    public int calculatePageFaults(int[] pages, int frameCount) {
        int pageFaults = 0;
        List<Integer> frames = new ArrayList<>();
        
        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            
            if (!frames.contains(page)) {
                pageFaults++;
                
                if (frames.size() < frameCount) {
                    frames.add(page);
                } else {
                    // Find the page that is used furthest in the future
                    int farthestIndex = -1;
                    int pageToReplace = -1;
                    
                    for (int j = 0; j < frames.size(); j++) {
                        int frame = frames.get(j);
                        int nextUse = getNextUse(pages, i, frame);
                        
                        if (nextUse == -1) {
                            pageToReplace = frame;
                            break;
                        }
                        
                        if (nextUse > farthestIndex) {
                            farthestIndex = nextUse;
                            pageToReplace = frame;
                        }
                    }
                    
                    frames.remove(Integer.valueOf(pageToReplace));
                    frames.add(page);
                }
            }
        }
        
        return pageFaults;
    }

    // Find when a page will be used next after a given index
    private int getNextUse(int[] pages, int currentIndex, int page) {
        for (int i = currentIndex + 1; i < pages.length; i++) {
            if (pages[i] == page) {
                return i;
            }
        }
        return -1;
    }
}
