import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier {
    private final int parties;
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition barrierCondition = lock.newCondition();

    public Barrier(int parties) {
        this.parties = parties;
    }

    public void await() throws InterruptedException {
        lock.lock();
        try {
            count++; 
            if (count < parties) {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier...");
                barrierCondition.await(); 
            } else {
                System.out.println(Thread.currentThread().getName() + " is the last thread! Releasing all threads.");
                count = 0;
                barrierCondition.signalAll(); 
            }
        } finally {
            lock.unlock();
        }
    }
}
