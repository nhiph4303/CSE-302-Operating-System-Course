package Assignment3;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int id;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}