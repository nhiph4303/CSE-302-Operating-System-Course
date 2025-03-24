package Assignment2;

import java.io.IOException;

public class VirtualMemoryManager {
    private int pageSize;
    private TLB tlb;
    PageTable pageTable;        
    private PhysicalMemory physicalMemory;
    private BackingStore backingStore;

    private int tlbMissCount = 0;
    private int pageFaultCount = 0;

    public VirtualMemoryManager(int pageSize, int pageNumber, int tlbEntryNum, int frameNumber,
                                String policyName, String backingStoreFileName) throws IOException {
        this.pageSize = pageSize;
        this.tlb = new TLB(tlbEntryNum, policyName);
        this.pageTable = new PageTable(pageNumber);
        this.physicalMemory = new PhysicalMemory(pageSize, frameNumber, policyName);
        this.backingStore = new BackingStore(pageSize, pageNumber, backingStoreFileName);
    }

    public byte read(int logicalAddress) throws IOException {
        logicalAddress = logicalAddress & 0xFFFF;
        int page = logicalAddress / this.pageSize;     
        int offset = logicalAddress % this.pageSize; 

        TLB.TLBResult tlbResult = this.tlb.refer(page);
        if (tlbResult.hit) {
            this.physicalMemory.refer(page);
            return this.physicalMemory.getByte(tlbResult.frame, offset);
        }

        this.tlbMissCount++;
        int frame = this.pageTable.refer(page);
        if (frame != -1) {
            this.tlb.update(tlbResult.position, page, frame);
            this.physicalMemory.refer(page);
            return this.physicalMemory.getByte(frame, offset);
        }

        this.pageFaultCount++;
        ReplacementPolicy.Result result = this.physicalMemory.refer(page);
        if (result.isFound()) {
            throw new IllegalStateException("Logic error: page was found in physical memory but pageTable says -1.");
        }
        frame = result.getPosition();
        int replacedPage = result.getReplacedValue();

        this.backingStore.swapIn(page, this.physicalMemory.getMomery(), frame);

        if (replacedPage != -1) {
            this.pageTable.update(replacedPage, -1);
        }

        this.pageTable.update(page, frame);

        TLB.TLBResult dummyResult = this.tlb.refer(page);
        this.tlb.update(dummyResult.position, page, frame);

        return this.physicalMemory.getByte(frame, offset);
    }

    public int getTlbMissCount() {
        return tlbMissCount;
    }

    public int getPageFaultCount() {
        return pageFaultCount;
    }
}
