package Assignment4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Nhập Ma trận Available
            System.out.println("Enter the number of resource types (e.g., A, B, C): ");
            int numResources = sc.nextInt();
            int[] available = new int[numResources];
            System.out.println("Enter the available resources: ");
            for (int i = 0; i < numResources; i++) {
                available[i] = sc.nextInt();
            }

            // Nhập Ma trận Maximum
            System.out.println("Enter the number of customers: ");
            int numCustomers = sc.nextInt();
            int[][] maximum = new int[numCustomers][numResources];
            System.out.println("Enter the Maximum resources for each customer:");
            for (int i = 0; i < numCustomers; i++) {
                System.out.println("Enter the max resources for customer P" + i + ": ");
                for (int j = 0; j < numResources; j++) {
                    maximum[i][j] = sc.nextInt();
                }
            }

            // Nhập Ma trận Allocation
            int[][] allocation = new int[numCustomers][numResources];
            System.out.println("Enter the Allocation matrix:");
            for (int i = 0; i < numCustomers; i++) {
                System.out.println("Enter the allocation for customer P" + i + ": ");
                for (int j = 0; j < numResources; j++) {
                    allocation[i][j] = sc.nextInt();
                }
            }

            Banker banker = new Banker(available, maximum, allocation);

            if (banker.isSafeState()) {
                System.out.println("The system is in a safe state.");
            } else {
                System.out.println("The system is not in a safe state.");
            }

            System.out.println("Enter the request for P1 (resources A B C): ");
            int[] requestP1 = new int[numResources];
            for (int i = 0; i < numResources; i++) {
                requestP1[i] = sc.nextInt();
            }

            if (banker.request(1, requestP1)) {
                System.out.println("Request by P1 can be granted.");
            } else {
                System.out.println("Request by P1 cannot be granted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}

// Enter the number of resource types (e.g., A, B, C):
// 3
// Enter the available resources:
// 3 3 2
// Enter the number of customers:
// 5
// Enter the Maximum resources for each customer:
// Enter the max resources for customer P0:
// 7 5 3
// Enter the max resources for customer P1:
// 3 2 2
// Enter the max resources for customer P2:
// 9 0 2
// Enter the max resources for customer P3:
// 2 2 2
// Enter the max resources for customer P4:
// 4 3 3
// Enter the Allocation matrix:
// Enter the allocation for customer P0:
// 0 1 0
// Enter the allocation for customer P1:
// 2 0 0
// Enter the allocation for customer P2:
// 3 0 2
// Enter the allocation for customer P3:
// 2 1 1
// Enter the allocation for customer P4:
// 0 0 2
// The system is in a safe state.
// Enter the request for P1 (resources A B C):
// 1 0 2
// Request by P1 can be granted.


// public class Main {
//     public static void main(String[] args) {
//         try {
//             // Ma trận Available
//             int[] available = {3, 3, 2};
            
//             // Ma trận Maximum
//             int[][] maximum = {
//                 {7, 5, 3},
//                 {3, 2, 2},
//                 {9, 0, 2},
//                 {2, 2, 2},
//                 {4, 3, 3}
//             };
            
//             // Ma trận Allocation
//             int[][] allocation = {
//                 {0, 1, 0},
//                 {2, 0, 0},
//                 {3, 0, 2},
//                 {2, 1, 1},
//                 {0, 0, 2}
//             };
            
//             Banker banker = new Banker(available, maximum, allocation);
            
//             if (banker.isSafeState()) {
//                 System.out.println("The system is in a safe state.");
//             } else {
//                 System.out.println("The system is not in a safe state.");
//             }
            
//             int[] requestP1 = {1, 0, 2};
//             if (banker.request(1, requestP1)) {
//                 System.out.println("Request by P1 can be granted.");
//             } else {
//                 System.out.println("Request by P1 cannot be granted.");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
