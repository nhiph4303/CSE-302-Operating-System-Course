public class Assignment1 {
    public static void main(String[] args) throws Exception {
        int[] pages = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };
        int n = 3;
        PageReplacementPolicy policy;
        Result result;
        int pageFaults;

        pageFaults = 0;
        policy = new FIFOPolicy(n);
        for (int i = 0; i < pages.length; i++) {
            result = policy.refer(pages[i]);
            if (result.isPageFault() == true) {
                pageFaults++;
            }
        }

        System.out.println("FIFO Policy: " + pageFaults + " page faults.");

        pageFaults = 0;
        policy = new OPTPolicy(n, pages);
        for (int i = 0; i < pages.length; i++) {
            result = policy.refer(pages[i]);
            if (result.isPageFault() == true) {
                pageFaults++;
            }
        }

        System.out.println("OPT Policy: " + pageFaults + " page faults.");

        pageFaults = 0;
        policy = new LRUPolicy(n);
        for (int i = 0; i < pages.length; i++) {
            result = policy.refer(pages[i]);
            if (result.isPageFault() == true) {
                pageFaults++;
            }
        }

        System.out.println("LRU Policy: " + pageFaults + " page faults.");
    }
}
