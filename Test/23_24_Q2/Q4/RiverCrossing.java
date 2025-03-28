package Q4;

public class RiverCrossing {
    private static final int MAX_CAPACITY = 4;  // Tối đa 4 người trong thuyền
    private int hackers = 0;
    private int employees = 0;
    private int boatLoad = 0;  // Số người hiện tại trên thuyền
    
    // Phương thức để hacker đến bờ sông
    public synchronized void hackerArrives() throws InterruptedException {
        while (boatLoad == MAX_CAPACITY) {
            wait();  // Chờ cho đến khi thuyền có không gian
        }
        
        hackers++;
        boatLoad++;
        System.out.println("Hacker arrives. Current hackers: " + hackers + ", employees: " + employees);
        
        // Nếu thuyền đầy đủ, cho thuyền qua sông
        if (boatLoad == MAX_CAPACITY) {
            crossRiver();
        }
    }
    
    // Phương thức để employee đến bờ sông
    public synchronized void employeeArrives() throws InterruptedException {
        while (boatLoad == MAX_CAPACITY) {
            wait();  // Chờ cho đến khi thuyền có không gian
        }
        
        employees++;
        boatLoad++;
        System.out.println("Employee arrives. Current hackers: " + hackers + ", employees: " + employees);
        
        // Nếu thuyền đầy đủ, cho thuyền qua sông
        if (boatLoad == MAX_CAPACITY) {
            crossRiver();
        }
    }
    
    // Phương thức khi thuyền đầy đủ, cho thuyền qua sông và reset trạng thái
    private synchronized void crossRiver() {
        System.out.println("Boat crossing river with " + boatLoad + " people.");
        
        // Reset lại các biến sau khi thuyền đã qua sông
        hackers = 0;
        employees = 0;
        boatLoad = 0;
        
        notifyAll();  // Thông báo các tiến trình chờ đợi rằng thuyền đã qua sông và họ có thể bắt đầu lại.
    }
}

