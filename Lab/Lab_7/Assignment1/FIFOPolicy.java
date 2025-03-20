import java.util.ArrayList;
import java.util.LinkedList;

public class FIFOPolicy implements PageReplacementPolicy {
    private int frameNum;
    private ArrayList<Integer> pages = new ArrayList<>();
    private LinkedList<Integer> queue = new LinkedList<>(); // queue of frames

    public FIFOPolicy(int frameNum) {
        this.frameNum = frameNum;
        this.queue = new LinkedList<>();
    }

    @Override
    public Result refer(int page) {
        // int replacePage = -1;
        // if (!this.queue.contains(page)) {
        //     if (this.queue.size() >= this.frameNum) {
        //         replacePage = this.queue.poll();
        //     }
        //     queue.add(page);
        //     return new Result(true, queue.size(), replacePage);
        // }
        // return new Result(false, queue.size(), replacePage);
        
        // Result res;
        // int frame;
        // int index = this.queue.indexOf(page);
        // if (index > -1) {
        //     frame = this.queue.get(index);
        //     res = new Result(false, frame, -1);
        // } else { //page fault
        //     int size = this.queue.size();
        //     if (size < this.frameNum) {
        //         this.queue.addLast(page);
        //         frame = size;
        //         res = new Result(true, frame, frame);
        //     } else { // replace
        //         int replacePage = this.queue.removeFirst();
        //         this.queue.addLast(page);
        //         frame = this.queue.indexOf(page);
        //         res = new Result(true, frame, replacePage);
        //     }
        // }
        // return res;

        Result res;
        int frame;

        frame = this.pages.indexOf(page);
        if (frame >= 0) { // found
            res = new Result(false, frame, -1);
        } else { // page fault
            int size = this.queue.size();
            if (size < this.frameNum) {
                this.pages.add(page); // add page to the end of list
                this.queue.addLast(page); // add page to the end of list
                res = new Result(true, size, -1);
            } else { // replace by FIFO algorithm
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
