import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    private final int[] buffer;
    private int count, head, tail;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBuffer(int capacity) {
        buffer = new int[capacity];
        count = head = tail = 0;
    }

    public void add(int value) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                System.out.println(Thread.currentThread().getName() + " is waiting (Buffer FULL)...");
                notFull.await();
            }
            buffer[tail] = value;
            tail = (tail + 1) % buffer.length;
            count++;
            System.out.println(Thread.currentThread().getName() + " added: " + value);
            notEmpty.signal(); // Notify waiting consumers
        } finally {
            lock.unlock();
        }
    }

    public int remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println(Thread.currentThread().getName() + " is waiting (Buffer EMPTY)...");
                notEmpty.await();
            }
            int value = buffer[head];
            head = (head + 1) % buffer.length;
            count--;
            System.out.println(Thread.currentThread().getName() + " removed: " + value);
            notFull.signal(); // Notify waiting producers
            return value;
        } finally {
            lock.unlock();
        }
    }
}
