package Q3;

// PhysicalAddressCalculator.java
public class PhysicalAddressCalculator {

    public static int calculatePhysicalAddress(int logicalAddress, int pageSize, int[] pageTable) {
        int pageNumber = logicalAddress / pageSize;
        int offset = logicalAddress % pageSize;
        return pageTable[pageNumber] * pageSize + offset;
    }

    public static void main(String[] args) {
        // Example usage
        int logicalAddress = 2456;
        int pageSize = 1024;
        int[] pageTable = {3, 6, 5, 1};  // Page to frame mapping
        int physicalAddress = calculatePhysicalAddress(logicalAddress, pageSize, pageTable);
        System.out.println("Physical Address: " + physicalAddress);
    }
}


