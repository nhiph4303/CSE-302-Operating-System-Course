import java.io.IOException;
import java.io.RandomAccessFile;

public class VirtualMemoryManager {
    private int pageSize;
    private TLB tlb;
    private PageTable pageTable;
    private PhysicalMemory physicalMemory;
    private BackingStore backingStore;

    private int tlbMissCount = 0;
    private int pageFaultCount = 0;

    public VirtualMemoryManager(int pageSize, int pageNumber, int tlbEntryNum, int frameNumber, String policyName,
            String backingStoreFileName) throws IOException {
        this.pageSize = pageSize;
        this.tlb = new TLB(tlbEntryNum, policyName);
        this.pageTable = new PageTable(pageNumber);
        this.physicalMemory = new PhysicalMemory(pageSize, frameNumber, policyName);
        this.backingStore = new BackingStore(pageSize, pageNumber, backingStoreFileName);
    }

    public byte read(int logicalAddress) throws IOException {
        int page = logicalAddress / this.pageSize;
        int offset = logicalAddress % this.pageSize;
        
        TLB.TLBResult tlbResult = this.tlb.refer(page);
        if (tlbResult.hit) { // TLB hit
            this.physicalMemory.refer(page);
            return this.physicalMemory.getByte(tlbResult.frame, offset);
        }
        // TLB miss
        this.tlbMissCount++;
        int frame = this.pageTable.refer(page);
        if (frame != -1) { // found
            // update TLB
            this.tlb.update(tlbResult.position, page, frame);
            this.physicalMemory.refer(page);
            return this.physicalMemory.getByte(frame, offset);
        }
        // page fault
        this.pageFaultCount++;
        ReplacementPolicy.Result result = this.physicalMemory.refer(page);
        if (result.isFound()) {
            throw new IllegalStateException("Must 'NOT FOUND' in physical memory");
        }
        frame = result.getPosition();
        int replacedPage = result.getReplacedValue();
        // swap in
        this.backingStore.swapIn(page, this.physicalMemory.getMomery(), frame);
        // update page table
        if (replacedPage != -1) { // Remove replaced page
            this.tlb.remove(replacedPage);
            this.pageTable.frames[replacedPage] = -1;
        }
        this.pageTable.frames[page] = frame;
        // update TLB
        this.tlb.update(tlbResult.position, page, frame);

        return this.physicalMemory.getByte(frame, offset);
    }

    public int getTlbMissCount() {
        return tlbMissCount;
    }

    public int getPageFaultCount() {
        return pageFaultCount;
    }
}

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

    public void remove(int page) {
        this.replacePolicy.remove(page);
        // Also clear the actual TLB entry if present
        for (int i = 0; i < this.entryNum; i++) {
            if (this.table[i][0] == page) {
                this.table[i][0] = -1;
                this.table[i][1] = -1;
            }
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

class PageTable {
    private int pageNumber;
    public int[] frames;

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
}

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
        ReplacementPolicy.Result result = this.replacePolicy.refer(page);
        return result;
    }

    public byte[] getMomery() {
        return this.memory;
    }
}

class BackingStore {
    private int pageSize;
    private int pageNumber;
    private RandomAccessFile bakingFile;

    public BackingStore(int pageSize, int pageNumber, String backingStoreFileName) throws IOException {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.bakingFile = new RandomAccessFile(backingStoreFileName, "r");

        if (this.bakingFile.length() != this.pageSize * this.pageNumber) {
            throw new IllegalArgumentException("Backing file " + backingStoreFileName + ": Invalid size.");
        }
    }

    public void swapIn(int page, byte[] memory, int frame) throws IOException {
        this.bakingFile.seek(this.pageSize * page);
        this.bakingFile.read(memory, this.pageSize * frame, this.pageSize);
    }
}
