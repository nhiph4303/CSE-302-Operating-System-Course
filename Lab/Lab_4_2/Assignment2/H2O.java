package Assignment2;

import java.util.*;
import java.util.concurrent.locks.*;

public class H2O {
	private int numOfHydrogen;
	private int numOfOxygen;
	private Lock lock = new ReentrantLock();
	private Condition hydrogenCond = this.lock.newCondition();
	private Condition oxygenCond = this.lock.newCondition();
	private List<String> history = new ArrayList<>();
	private int numOfH2O;

	public void hydrogen() throws InterruptedException {
		this.lock.lock();
		try {
			if (this.numOfHydrogen > 0 && this.numOfOxygen > 0) {
				this.hydrogenCond.signal();
				this.numOfHydrogen--;
				this.oxygenCond.signal();
				this.numOfOxygen--;
				this.numOfH2O++;
				this.history.add("Enough to form, "+numOfH2O + " H2O were created!");
				this.history.add("There are " + this.numOfHydrogen + " hydro left");
				this.history.add("There are " + this.numOfOxygen + " oxy left");
				
			} else {
				this.numOfHydrogen++;
				this.history.add("Hydrogen await");
				this.history.add("There are " + this.numOfHydrogen + " hydro available");
				this.history.add("There are " + this.numOfOxygen + " oxy available");
				this.hydrogenCond.await();

			}
		} finally {
			this.lock.unlock();
		}
	}

	public void oxygen() throws InterruptedException {
		this.lock.lock();
		try {
			if (this.numOfHydrogen > 1 && this.numOfOxygen >= 0) {
				this.hydrogenCond.signal();
				this.hydrogenCond.signal();
				this.numOfHydrogen -= 2;
				this.numOfH2O++;
				this.history.add("Enough to form, "+ numOfH2O + " H20 were created!");
				this.history.add("There are " + this.numOfHydrogen + " hydro left");
				this.history.add("There are " + this.numOfOxygen + " oxy left");
				
			} else {
				this.numOfOxygen++;
				this.history.add("Oxygen await");
				this.history.add("There are " + this.numOfHydrogen + " hydro available");
				this.history.add("There are " + this.numOfOxygen + " oxy available");
				this.oxygenCond.await();

			}
		} finally {
			this.lock.unlock();
		}
	}

	public List<String> getHistory() {
		return history;
	}

	public void setHistory(List<String> history) {
		this.history = history;
	}

	public int getNumOfH2O() {
		return numOfH2O;
	}

	public void setNumOfH2O(int numOfH2O) {
		this.numOfH2O = numOfH2O;
	}

}
