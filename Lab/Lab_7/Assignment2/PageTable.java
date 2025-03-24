package Assignment2;

class PageTable {
    private int pageNumber;
    private int[] frames;

    public PageTable(int pageNumber) {
        this.pageNumber = pageNumber;
        this.frames = new int[this.pageNumber];
        for (int i = 0; i < frames.length; i++) {
            this.frames[i] = -1;
        }
    }

    public int refer(int page) {
        return this.frames[page];
    }

    public void update(int page, int frame) {
        this.frames[page] = frame;
    }
}
