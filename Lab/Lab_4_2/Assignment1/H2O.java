import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {
    private boolean fairness;
    private int hydroCount = 0;
    private int oxyCount = 0;

    private ReentrantLock lock;
    private Condition hydroCond;
    private Condition oxyCond;

    public H2O(boolean fairness) {
        this.fairness = fairness;
        this.lock = new ReentrantLock(fairness);
        this.hydroCond = this.lock.newCondition();
        this.oxyCond = this.lock.newCondition();
    }

    public void hydrogen() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.hydroCount == 2) { 
                this.hydroCond.await(); 
            }
            this.hydroCount++;
            System.out.println("H");
            if (this.hydroCount == 2 && this.oxyCount == 1) {
                System.out.println("Molecule H2O formed.");

                this.hydroCount = 0;
                this.oxyCount = 0;

                if (this.fairness) {
                    this.hydroCond.signal();
                    this.hydroCond.signal();
                    this.oxyCond.signal();
                } else {
                    this.hydroCond.signalAll();
                    this.oxyCond.signalAll();
                }
            } else {
                this.oxyCond.signal(); 
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void oxygen() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.oxyCount == 1) { 
                this.oxyCond.await(); 
            }
            this.oxyCount++;
            System.out.println("O");
            if (this.hydroCount == 2 && this.oxyCount == 1) {
                System.out.println("Molecule H2O formed.");

                this.hydroCount = 0;
                this.oxyCount = 0;

                if (this.fairness) {
                    this.hydroCond.signal();
                    this.hydroCond.signal();
                    this.oxyCond.signal();
                } else {
                    this.hydroCond.signalAll();
                    this.oxyCond.signalAll();
                }
            } else {
                this.hydroCond.signal(); 
            }
        } finally {
            this.lock.unlock();
        }
    }
}