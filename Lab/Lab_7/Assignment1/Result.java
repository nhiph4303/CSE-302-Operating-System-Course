public class Result {
    private boolean pageFault;
    private int frame;
    private int replacePage;

    public Result(boolean pageFault, int frame, int replacePage) {
        this.pageFault = pageFault;
        this.frame = frame;
        this.replacePage = replacePage;
    }

    public boolean isPageFault() {
        return pageFault;
    }

    public int getFrame() {
        return frame;
    }

    public int getReplacePage() {
        return replacePage;
    }

    @Override
    public String toString() {
        return this.pageFault + ", " + this.frame + ", " + this.replacePage;
    }
}
