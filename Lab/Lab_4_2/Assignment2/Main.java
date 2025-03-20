package Assignment2;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		H2O h2o = new H2O();
		Runnable hydrogen = new Runnable() {

			@Override
			public void run() {
				try {
					h2o.hydrogen();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable oxygen = new Runnable() {

			@Override
			public void run() {
				try {
					h2o.oxygen();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of H2O: ");
		int numOfH2O = sc.nextInt();

		Thread[] hydro = new Thread[numOfH2O * 2];
		Thread[] oxy = new Thread[numOfH2O];
		for (int i = 0; i < oxy.length; i++) {

			oxy[i] = new Thread(oxygen);
			oxy[i].start();
		}
		for (int i = 0; i < hydro.length; i++) {
			hydro[i] = new Thread(hydrogen);
			hydro[i].start();

		}
		for (int i = 0; i < oxy.length; i++) {

			try {
				oxy[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < hydro.length; i++) {

			try {
				hydro[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (String each : h2o.getHistory()) {
			System.out.println(each);
		}
		System.out.println("There are " + h2o.getNumOfH2O() + " H2O were formed!");
	}
}
