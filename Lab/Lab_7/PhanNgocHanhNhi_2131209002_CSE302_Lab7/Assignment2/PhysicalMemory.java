package Assignment2;

class PhysicalMemory {
    private int frameSize;
    private int frameNumber;
    private byte[] memory;
    private ReplacementPolicy replacePolicy;

    public PhysicalMemory(int frameSize, int frameNumber, String policyName) {
        this.frameSize = frameSize;
        this.frameNumber = frameNumber;
        this.memory = new byte[this.frameSize * this.frameNumber];

        if (policyName.trim().equalsIgnoreCase("FIFO")) {
            this.replacePolicy = new FIFOPolicy(this.frameNumber);
        } else if (policyName.trim().equalsIgnoreCase("LRU")) {
            this.replacePolicy = new LRUPolicy(this.frameNumber);
        } else {
            throw new IllegalArgumentException("Unknown page replacement policy: " + policyName);
        }
    }

    public byte getByte(int frame, int offset) {
        return this.memory[this.frameSize * frame + offset];
    }

    public ReplacementPolicy.Result refer(int page) {
        return this.replacePolicy.refer(page);
    }

    public byte[] getMomery() {
        return this.memory;
    }
}
