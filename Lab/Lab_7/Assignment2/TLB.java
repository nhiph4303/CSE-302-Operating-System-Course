package Assignment2;

class TLB {
    private int entryNum;
    private int[][] table;
    private ReplacementPolicy replacePolicy;

    public TLB(int entryNum, String policyName) {
        this.entryNum = entryNum;
        this.table = new int[this.entryNum][2]; 

        for (int i = 0; i < this.entryNum; i++) {
            this.table[i][0] = -1;
            this.table[i][1] = -1;
        }

        if (policyName.trim().equalsIgnoreCase("FIFO")) {
            this.replacePolicy = new FIFOPolicy(this.entryNum);
        } else if (policyName.trim().equalsIgnoreCase("LRU")) {
            this.replacePolicy = new LRUPolicy(this.entryNum);
        } else {
            throw new IllegalArgumentException("Unknown page replacement policy: " + policyName);
        }
    }

    public void update(int position, int page, int frame) {
        this.table[position][0] = page;
        this.table[position][1] = frame;
    }

    public TLBResult refer(int page) {
        ReplacementPolicy.Result policyResult = this.replacePolicy.refer(page);
        TLBResult tlbResult = new TLBResult();
        tlbResult.hit = policyResult.isFound();
        tlbResult.position = policyResult.getPosition();
        tlbResult.page = page;
        tlbResult.frame = this.table[tlbResult.position][1];
        tlbResult.replacedPage = policyResult.getReplacedValue();
        return tlbResult;
    }

    public class TLBResult {
        boolean hit;
        int page;
        int frame;
        int position;
        int replacedPage;
    }
}
