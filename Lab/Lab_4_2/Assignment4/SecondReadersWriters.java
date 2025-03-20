package Assignment4;
import java.util.concurrent.locks.*;

public class SecondReadersWriters {
    private int readers = 0;       
    private int writers = 0;       
    private int waitingWriters = 0; 
    private final Lock lock = new ReentrantLock();
    private final Condition readCondition = lock.newCondition();
    private final Condition writeCondition = lock.newCondition();

    public SecondReadersWriters() {
    }

    public void readEnter(int readerId) throws InterruptedException {
        lock.lock();
        try {
            while (writers > 0 || waitingWriters > 0) {
                System.out.println("Reader " + readerId + " is waiting (Writer is waiting/writing)...");
                readCondition.await();
            }
            readers++;
            System.out.println("Reader " + readerId + " is reading. Active Readers: " + readers);
        } finally {
            lock.unlock();
        }
    }

    public void readExit(int readerId) {
        lock.lock();
        try {
            readers--;
            System.out.println("Reader " + readerId + " has finished reading.");
            if (readers == 0) {
                writeCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void writeEnter(int writerId) throws InterruptedException {
        lock.lock();
        try {
            waitingWriters++;
            while (readers > 0 || writers > 0) {
                System.out.println("Writer " + writerId + " is waiting (Readers or other Writer are active)...");
                writeCondition.await();
            }
            waitingWriters--; 
            writers++;
            System.out.println("Writer " + writerId + " is WRITING...");
        } finally {
            lock.unlock();
        }
    }
    public void writeExit(int writerId) {
        lock.lock();
        try {
            writers--;
            System.out.println("Writer " + writerId + " has finished writing.");
            if (waitingWriters > 0) {
                writeCondition.signal(); 
            } else {
                readCondition.signalAll(); 
            }
        } finally {
            lock.unlock();
        }
    }
}
