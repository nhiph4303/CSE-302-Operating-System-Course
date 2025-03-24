package Assignment1;
import java.util.ArrayList;
import java.util.LinkedList;

public class FIFOPolicy implements PageReplacementPolicy {
    private int frameNum;
    private ArrayList<Integer> pages = new ArrayList<>();
    private LinkedList<Integer> queue = new LinkedList<>(); 

    public FIFOPolicy(int frameNum) {
        this.frameNum = frameNum;
        this.queue = new LinkedList<>();
    }

    @Override
    public Result refer(int page) {
        Result res;
        int frame;

        frame = this.pages.indexOf(page);
        if (frame >= 0) { 
            res = new Result(false, frame, -1);
        } else { 
            int size = this.queue.size();
            if (size < this.frameNum) {
                this.pages.add(page); 
                this.queue.addLast(page);
                res = new Result(true, size, -1);
            } else { 
                int replacePage = this.queue.removeFirst();
                frame = this.pages.indexOf(replacePage);
                this.pages.set(frame, page);
                this.queue.addLast(page);
                res = new Result(true, frame, replacePage);
            }
        }
        return res;
    }
}
