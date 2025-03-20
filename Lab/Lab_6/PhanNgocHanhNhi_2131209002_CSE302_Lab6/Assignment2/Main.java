package Assignment2;
import java.util.*;

public class Main {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        ContiguousAllocator cma = new ContiguousAllocator(4096); 

        while (true) {
            System.out.print("allocator> ");
            String cmdLine = keyboard.nextLine().trim().toUpperCase();

            if (cmdLine.equals("X")) {
                System.out.println("Exiting the allocator...");
                break;
            }

            String[] components = cmdLine.split("\\s+");

            switch (components[0]) {
                case "RQ":
                    if (components.length != 4) {
                        System.out.println("Invalid command format for RQ. Correct format: RQ <process_name> <size> <allocation_method>");
                        break;
                    }
                    String processName = components[1];
                    int memorySize;
                    try {
                        memorySize = Integer.parseInt(components[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid memory size. Please enter a valid integer.");
                        break;
                    }
                    String allocationMethod = components[3];
                    boolean result;

                    switch (allocationMethod) {
                        case "F":
                            result = cma.requestFirstFit(processName, memorySize);
                            break;
                        case "B":
                            result = cma.requestBestFit(processName, memorySize);
                            break;
                        case "W":
                            result = cma.requestWorstFit(processName, memorySize);
                            break;
                        default:
                            System.out.println("Invalid allocation method. Use F, B, or W.");
                            continue;
                    }

                    if (!result) {
                        System.out.println("Cannot allocate memory for process " + processName);
                    }
                    break;

                case "RL": 
                    if (components.length != 2) {
                        System.out.println("Invalid command format for RL. Correct format: RL <process_name>");
                        break;
                    }
                    String releaseProcessName = components[1];
                    cma.releaseMemory(releaseProcessName);
                    break;

                case "C":
                    cma.compactMemory();
                    System.out.println("Memory has been compacted.");
                    break;

                case "STAT":
                    cma.stat(); 
                    break;

                default:
                    System.out.println("Invalid command. Please enter a valid command.");
            }
        }
    }
}
