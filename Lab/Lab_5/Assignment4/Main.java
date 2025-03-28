package Assignment4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] available = { 3, 3, 2 };
        int[][] maximum = {
                { 7, 5, 3 },
                { 3, 2, 2 },
                { 9, 0, 2 },
                { 2, 2, 2 },
                { 4, 3, 3 }
        };
        int[][] allocation = {
                { 0, 1, 0 },
                { 2, 0, 0 },
                { 3, 0, 2 },
                { 2, 1, 1 },
                { 0, 0, 2 }
        };

        try {
            Banker banker = new Banker(available, maximum, allocation);

            int[] p1Request = { 1, 0, 2 };
            boolean canGrant = banker.request(1, p1Request);

            System.out.println("Can P1’s request be granted: " + canGrant);

            ArrayList<Integer> safeSequence = banker.isSafeState();
            if (!safeSequence.isEmpty()) {
                System.out.println("The system is in a safe state with the safe sequence: " + safeSequence);
            } else {
                System.out.println("The system is not in a safe state.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
