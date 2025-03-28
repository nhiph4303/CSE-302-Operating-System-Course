package Q3;

// OptimalPageReplacement.java
import java.util.*;

public class OptimalPageReplacement {

    public static int optimalPageReplacement(int[] pages, int frameCount) {
        int pageFaults = 0;
        int[] frames = new int[frameCount];
        Arrays.fill(frames, -1);  // -1 means the frame is empty

        for (int i = 0; i < pages.length; i++) {
            int currentPage = pages[i];

            // Check if page is already in frames
            if (!contains(frames, currentPage)) {
                pageFaults++;
                int farthestIndex = -1;
                int frameToReplace = -1;

                // Find the page to replace (optimal)
                for (int j = 0; j < frameCount; j++) {
                    if (frames[j] == -1) {
                        frameToReplace = j;
                        break;
                    } else {
                        // Look ahead for the page that is needed the farthest in future
                        int nextUse = getNextUse(pages, i, frames[j]);
                        if (nextUse == -1) {
                            frameToReplace = j;
                            break;
                        } else if (nextUse > farthestIndex) {
                            farthestIndex = nextUse;
                            frameToReplace = j;
                        }
                    }
                }

                frames[frameToReplace] = currentPage;
            }
        }

        return pageFaults;
    }

    private static boolean contains(int[] frames, int page) {
        for (int frame : frames) {
            if (frame == page) {
                return true;
            }
        }
        return false;
    }

    private static int getNextUse(int[] pages, int currentIndex, int page) {
        for (int i = currentIndex + 1; i < pages.length; i++) {
            if (pages[i] == page) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Example usage
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 3};
        int frameCount = 4;
        int pageFaults = optimalPageReplacement(pages, frameCount);
        System.out.println("Total page faults using Optimal Algorithm: " + pageFaults);
    }
}


