package Assignment1;
public class Main {
    public static void main(String[] args) {
        int[] pages = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };
        int n = 3; 
        PageReplacementPolicy policy;
        Result result;
        int pageFaults;

        // FIFO
        pageFaults = 0;
        policy = new FIFOPolicy(n);
        for (int i = 0; i < pages.length; i++) {
            result = policy.refer(pages[i]);
            if (result.isPageFault()) {
                pageFaults++;
            }
        }

        System.out.println("FIFO Policy: " + pageFaults + " page faults.");

        // OPT
        pageFaults = 0;
        policy = new OPTPolicy(n, pages);
        for (int i = 0; i < pages.length; i++) {
            result = policy.refer(pages[i]);
            if (result.isPageFault()) {
                pageFaults++;
            }
        }
        System.out.println("OPT Policy: " + pageFaults + " page faults.");

        // LRU
        pageFaults = 0;
        policy = new LRUPolicy(n);
        for (int i = 0; i < pages.length; i++) {
            result = policy.refer(pages[i]);
            if (result.isPageFault()) {
                pageFaults++;
            }
        }
        System.out.println("LRU Policy: " + pageFaults + " page faults.");
    }
}

// public class Main {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         System.out.print("Enter number of frames: ");
//         int n = sc.nextInt();

//         System.out.print("Enter length of page-reference string: ");
//         int length = sc.nextInt();

//         int[] pages = new int[length];
//         System.out.println("Enter the page references (space or newline separated): ");
//         for (int i = 0; i < length; i++) {
//             pages[i] = sc.nextInt();
//         }
//         sc.close();

//         PageReplacementPolicy policy;
//         Result result;
//         int pageFaults;

//         // FIFO
//         pageFaults = 0;
//         policy = new FIFOPolicy(n);
//         for (int page : pages) {
//             result = policy.refer(page);
//             if (result.isPageFault()) {
//                 pageFaults++;
//             }
//         }
//         System.out.println("FIFO Policy: " + pageFaults + " page faults.");

//         // OPT
//         pageFaults = 0;
//         policy = new OPTPolicy(n, pages);
//         for (int page : pages) {
//             result = policy.refer(page);
//             if (result.isPageFault()) {
//                 pageFaults++;
//             }
//         }
//         System.out.println("OPT Policy: " + pageFaults + " page faults.");

//         // LRU
//         pageFaults = 0;
//         policy = new LRUPolicy(n);
//         for (int page : pages) {
//             result = policy.refer(page);
//             if (result.isPageFault()) {
//                 pageFaults++;
//             }
//         }
//         System.out.println("LRU Policy: " + pageFaults + " page faults.");
//     }
// }