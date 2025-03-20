import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OldBridge {
    private final int maxCars = 3;
    private int count = 0;
    private int currentDirection = -1;
    private final Lock lock = new ReentrantLock();
    private final Condition bridgeCondition = lock.newCondition();

    public void arriveBridge(int direction) throws InterruptedException {
        lock.lock();
        try {
            while (count == maxCars || (count > 0 && currentDirection != direction)) {
                System.out.println(Thread.currentThread().getName() + " is waiting to enter (Direction: " + direction + ")");
                bridgeCondition.await();
            }
            count++;
            currentDirection = direction;
            System.out.println(Thread.currentThread().getName() + " is crossing the bridge (Direction: " + direction + ")");
        } finally {
            lock.unlock();
        }
    }

    public void exitBridge() {
        lock.lock();
        try {
            count--;
            System.out.println(Thread.currentThread().getName() + " has exited the bridge.");
            if (count == 0) {
                System.out.println("The bridge is now empty. Direction can change.");
                currentDirection = -1;
            }
            bridgeCondition.signalAll(); // Notify waiting cars
        } finally {
            lock.unlock();
        }
    }
}
