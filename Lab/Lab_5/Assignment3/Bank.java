package Assignment3;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();

    public Bank(int accountNumber, double balance) {
        for (int i = 0; i < accountNumber; i++) {
            Account acc = new Account(i, balance);
            this.accounts.add(acc);
        }
    }

    private Account find(int id) {
        for (Account account : this.accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    public boolean transaction(int fromId, int toId, double amount) {
        Account from = this.find(fromId);
        Account to = this.find(toId);

        if (from == null || to == null) {
            return false;
        }

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
            firstLock.getLock().unlock();
            secondLock.getLock().unlock();
        }
    }
}
