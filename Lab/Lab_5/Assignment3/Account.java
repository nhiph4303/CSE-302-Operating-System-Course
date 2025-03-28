package Assignment3;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int id;
    private double balance;
    private ReentrantLock lock = new ReentrantLock();

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public double getBalance() {
        this.lock.lock();
        try {
            return balance;
        } finally {
            this.lock.unlock();
        }
    }

    public void setBalance(double balance) {
        this.lock.lock();
        try {
            this.balance = balance;
        } finally {
            this.lock.unlock();
        }
    }

    public int getId() {
        return id;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
