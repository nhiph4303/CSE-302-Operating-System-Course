import java.util.concurrent.locks.*;

public class SecondReadersWriters {
    private int readCount = 0;
    private int writeCount = 0;
    private int waitingWriters = 0;

    private Lock lock = new ReentrantLock();
    private Condition readCond = this.lock.newCondition();
    private Condition writeCond = this.lock.newCondition();

    public SecondReadersWriters() {
    }

    public void readEnter() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.writeCount > 0 || this.waitingWriters > 0) {
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
            this.waitingWriters++;
            while (this.readCount > 0 || this.writeCount > 0) {
                this.writeCond.await();
            }
            this.waitingWriters--;
            this.writeCount++;
        } finally {
            this.lock.unlock();
        }
    }

    public void writeExit() {
        this.lock.lock();
        try {
            this.writeCount--;
            if (this.waitingWriters > 0) {
                this.writeCond.signal();
            } else {
                this.readCond.signalAll();
            }
        } finally {
            this.lock.unlock();
        }
    }
}
