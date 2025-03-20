import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TSQueue {
    private final LinkedList<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    public void addLast(int value) {
        lock.lock();
        try {
            queue.addLast(value);
            System.out.println(Thread.currentThread().getName() + " added: " + value);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int removeFirst() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " is waiting for elements...");
                notEmpty.await();
            }
            int value = queue.removeFirst();
            System.out.println(Thread.currentThread().getName() + " removed: " + value);
            return value;
        } finally {
            lock.unlock();
        }
    }
}
