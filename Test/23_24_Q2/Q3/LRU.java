package Q3;

import java.util.*;

public class LRU extends PageReplacement {

    @Override
    public int calculatePageFaults(int[] pages, int frameCount) {
        int pageFaults = 0;
        LinkedList<Integer> frames = new LinkedList<>();
        LinkedList<Integer> pageOrder = new LinkedList<>();
        
        for (int page : pages) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() < frameCount) {
                    frames.add(page);
                } else {
                    // Remove the least recently used page
                    int leastUsed = pageOrder.removeFirst();
                    frames.remove(Integer.valueOf(leastUsed));
                    frames.add(page);
                }
            }
            pageOrder.add(page);
        }
        
        return pageFaults;
    }
}

