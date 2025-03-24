package Assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
   public static void main(String[] args) throws IOException {
        String backingFileName = "BACKING_STORE.bin";
        int pageSize = 256;
        int pageNumber = 256;
        int frameNumber = 32;
        int tlbEntryNum = 16;

        BufferedReader addressReader = null;
        try {
            VirtualMemoryManager vmm = new VirtualMemoryManager(
                    pageSize, pageNumber, tlbEntryNum, frameNumber,
                    "FIFO", backingFileName);

            byte[] backingStore = Files.readAllBytes(Paths.get(backingFileName));

            addressReader = new BufferedReader(new FileReader("addresses.txt"));
            String line = addressReader.readLine();
            int lineCount = 1;
            while (line != null) {
                int logicalAddress = Integer.parseInt(line.trim());
                byte value = vmm.read(logicalAddress);

                if (value != backingStore[logicalAddress & 0xFFFF]) {
                    System.err.println("Line " + lineCount + " - " + logicalAddress
                            + " - read value: " + value
                            + ", expected: " + backingStore[logicalAddress & 0xFFFF]);
                }

                line = addressReader.readLine();
                lineCount++;
            }
            System.out.println("Done");
            System.out.println("TLB miss count: " + vmm.getTlbMissCount());
            System.out.println("Page fault count: " + vmm.getPageFaultCount());
        } finally {
            if (addressReader != null) {
                addressReader.close();
            }
        }
    }
}
