package Assignment3;

import java.util.*;

public class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();

    public Bank(int accountNum, int balance) {
        for (int i = 0; i < accountNum; i++) {
            Account acc = new Account(i, balance);
            this.accounts.add(acc);
        }
    }

    protected Account find(int id) {
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getId() == id)
                return this.accounts.get(i);
        }
        return null;
    }

    public boolean transaction(int fromId, int toId, double amount) {
        Account from = this.find(fromId);
        Account to = this.find(toId);

        if (from == null || to == null)
            return false;

        Account firstLock = (System.identityHashCode(from) < System.identityHashCode(to)) ? from : to;
        Account secondLock = (firstLock == from) ? to : from;

        firstLock.getLock().lock();
        secondLock.getLock().lock();

        try {
            if (from.getBalance() < amount) {
                return false;
            }
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            return true;
        } finally {
            secondLock.getLock().unlock();
            firstLock.getLock().unlock();
        }
    }
}
