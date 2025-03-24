package Assignment3;
import java.util.concurrent.locks.*;

public class FirstReadersWriters {
    private int readCount = 0; 
    private int writeCount = 0; 

    private Lock lock = new ReentrantLock();
    private Condition readCond = this.lock.newCondition();
    private Condition writeCond = this.lock.newCondition();

    public FirstReadersWriters() {
    }

    public void readEnter(int readerId) throws InterruptedException {
        this.lock.lock();
        try {
            while (this.writeCount > 0) { 
                System.out.println("Reader " + readerId + " is waiting (Writer is writing)...");
                this.readCond.await();
            }
            this.readCount++;
            System.out.println("Reader " + readerId + " is reading. Active Readers: " + this.readCount);
        } finally {
            this.lock.unlock();
        }
    }

    public void readExit(int readerId) {
        this.lock.lock();
        try {
            this.readCount--;
            System.out.println("Reader " + readerId + " has finished reading.");
            if (this.readCount == 0) {
                this.writeCond.signal(); 
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void writeEnter(int writerId) throws InterruptedException {
        this.lock.lock();
        try {
            while (this.readCount > 0 || this.writeCount > 0) { 
                System.out.println("Writer " + writerId + " is waiting (Readers or other Writer are active)...");
                this.writeCond.await();
            }
            this.writeCount++;
            System.out.println("Writer " + writerId + " is WRITING...");
        } finally {
            this.lock.unlock();
        }
    }

    public void writeExit(int writerId) {
        this.lock.lock();
        try {
            this.writeCount--;
            System.out.println("Writer " + writerId + " has finished writing.");
            if (this.writeCount == 0) {
                this.readCond.signalAll(); 
                this.writeCond.signal();  
            }
        } finally {
            this.lock.unlock();
        }
    }
    
}
