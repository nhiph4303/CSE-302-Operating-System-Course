package Q4;

// SSTFDiskScheduling.java
import java.util.Arrays;

public class SSTFDiskScheduling {

    public static int diskSchedulingSSTF(int[] requests, int initialPosition) {
        int totalSeekTime = 0;
        int currentPosition = initialPosition;

        Arrays.sort(requests);  // Sort the requests in ascending order

        while (requests.length > 0) {
            int closestRequest = findClosestRequest(requests, currentPosition);
            totalSeekTime += Math.abs(currentPosition - closestRequest);
            currentPosition = closestRequest;

            // Remove the processed request
            requests = removeRequest(requests, closestRequest);
        }

        return totalSeekTime;
    }

    private static int findClosestRequest(int[] requests, int currentPosition) {
        int closest = requests[0];
        int minDistance = Math.abs(currentPosition - closest);
        for (int request : requests) {
            int distance = Math.abs(currentPosition - request);
            if (distance < minDistance) {
                closest = request;
                minDistance = distance;
            }
        }
        return closest;
    }

    private static int[] removeRequest(int[] requests, int request) {
        int[] newRequests = new int[requests.length - 1];
        int index = 0;
        for (int i = 0; i < requests.length; i++) {
            if (requests[i] != request) {
                newRequests[index++] = requests[i];
            }
        }
        return newRequests;
    }

    public static void main(String[] args) {
        // Example usage
        int[] requests = {82, 170, 43, 140, 24, 16, 190};
        int initialPosition = 50;
        int totalSeekTime = diskSchedulingSSTF(requests, initialPosition);
        System.out.println("Total seek time using SSTF: " + totalSeekTime + " milliseconds");
    }
}

