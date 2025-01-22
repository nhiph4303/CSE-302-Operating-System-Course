// PrimeCalculator.java
package Assignment3;

public class PrimeCalculator extends Thread {
    private final SharedData sharedData;

    public PrimeCalculator(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        int limit = sharedData.getLimit();
        System.out.println("Prime numbers less than or equal to " + limit + ":");

        for (int num = 2; num <= limit; num++) {
            if (isPrime(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }

    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}