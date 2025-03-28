package Q3;
import java.util.*;

public class FIFO extends PageReplacement {
    
    @Override
    public int calculatePageFaults(int[] pages, int frameCount) {
        int pageFaults = 0;
        LinkedList<Integer> frames = new LinkedList<>();
        
        for (int page : pages) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() < frameCount) {
                    frames.add(page);
                } else {
                    frames.removeFirst();
                    frames.add(page);
                }
            }
        }
        
        return pageFaults;
    }
}

public abstract class PageReplacement {
    // Abstract method to be implemented by each algorithm class
    public abstract int calculatePageFaults(int[] pages, int frameCount);
}
