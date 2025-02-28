import java.util.concurrent.locks.*;

public class FirstReadersWriters {
    private int readCount = 0;
    private int writeCount = 0;

    private Lock lock = new ReentrantLock();
    private Condition readCond = this.lock.newCondition();
    private Condition writeCond = this.lock.newCondition();

    public FirstReadersWriters() {
    }

    public void readEnter() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.writeCount > 0) {
                this.readCond.await();
            }
            this.readCount++;
        } finally {
            this.lock.unlock();
        }
    }

    public void readExit() {
        this.lock.lock();
        try {
            this.readCount--;
            if (this.readCount == 0) {
                this.writeCond.signal();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void writeEnter() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.readCount > 0 || this.writeCount > 0) {
                this.writeCond.await();
            }
            this.writeCount++;
        } finally {
            this.lock.unlock();
        }
    }

    public void writeExit() {
        this.lock.lock();
        try {
            this.writeCount--;
            if (this.writeCount == 0) {
                this.readCond.signalAll();
                this.writeCond.signal();
            }
        } finally {
            this.lock.unlock();
        }
    }
}
