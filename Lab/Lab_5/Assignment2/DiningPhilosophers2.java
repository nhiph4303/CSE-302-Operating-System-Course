package Assignment2;

import java.util.concurrent.locks.*;

public class DiningPhilosophers2 {
    private static final int N = 5;

    private int remainForks = N;
    private int eatingCount = 0;

    private Lock lock = new ReentrantLock();
    private Condition cond = this.lock.newCondition();

    public DiningPhilosophers2() {

    }

    public void getForks() {
        this.lock.lock();
        try {
            // get the first fork
            if (this.remainForks > 1 || (this.remainForks == 1 && this.eatingCount > 0)) {
                this.remainForks-=2;
                this.eatingCount++;
            }else{
                try{
                    cond.await();
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            // get the second fork
        } finally {
            this.lock.unlock();
        }

    }

    public void releaseForks() {
        this.lock.lock();
        try {
            this.remainForks += 2;
            this.eatingCount--;
            cond.signalAll();
        } finally {
            this.lock.lock();
        }
    }
}
