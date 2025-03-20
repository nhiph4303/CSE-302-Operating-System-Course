import java.util.Scanner;

public class VirtualAddressCalculator {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        final int PAGE_SIZE = 4096; // 4 KB

        System.out.println("Enter the virtual address: ");
        int virtualAddress = sc.nextInt();
        sc.close();

        
        int pageNumber = virtualAddress / PAGE_SIZE;
        int offset = virtualAddress % PAGE_SIZE;

        System.out.println("The address " + virtualAddress + " contains:");
        System.out.println("    Page number = " + pageNumber);
        System.out.println("    Offset = " + offset);
    }
}
