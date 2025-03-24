package Assignment2;

import java.io.IOException;
import java.io.RandomAccessFile;

class BackingStore {
    private int pageSize;
    private int pageNumber;
    private RandomAccessFile bakingFile;

    public BackingStore(int pageSize, int pageNumber, String backingStoreFileName) throws IOException {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.bakingFile = new RandomAccessFile(backingStoreFileName, "r");
        if (this.bakingFile.length() != (long)this.pageSize * this.pageNumber) {
            throw new IllegalArgumentException("Backing file " + backingStoreFileName + ": Invalid size.");
        }
    }

    public void swapIn(int page, byte[] memory, int frame) throws IOException {
        this.bakingFile.seek((long)this.pageSize * page);
        this.bakingFile.read(memory, this.pageSize * frame, this.pageSize);
    }
}
